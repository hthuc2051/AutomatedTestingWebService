package com.fpt.automatedtesting.practicalexams;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fpt.automatedtesting.common.CustomConstant.*;
import static com.fpt.automatedtesting.common.PathConstants.*;

import com.fpt.automatedtesting.common.*;
import com.fpt.automatedtesting.duplicatedcode.DuplicatedCode;
import com.fpt.automatedtesting.duplicatedcode.DuplicatedCodeDetails;
import com.fpt.automatedtesting.duplicatedcode.DuplicatedCodeRepository;
import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeDto;
import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeRequest;
import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeResponse;
import com.fpt.automatedtesting.duplicatedcode.dtos.FileVectors;
import com.fpt.automatedtesting.githubresult.GithubResultService;
import com.fpt.automatedtesting.githubresult.dtos.GitHubFileDuplicateDTO;
import com.fpt.automatedtesting.practicalexams.dtos.*;
import com.fpt.automatedtesting.students.StudentReportDto;
import com.fpt.automatedtesting.submissions.dtos.SubmissionReport;
import com.fpt.automatedtesting.submissions.dtos.request.SubmissionDetailsDto;
import com.fpt.automatedtesting.submissions.StudentSubmissionDetails;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.lecturers.Lecturer;
import com.fpt.automatedtesting.lecturers.LecturerRepository;
import com.fpt.automatedtesting.scripts.Script;
import com.fpt.automatedtesting.scripts.ScriptRepository;
import com.fpt.automatedtesting.students.Student;
import com.fpt.automatedtesting.subjects.Subject;
import com.fpt.automatedtesting.subjects.SubjectRepository;
import com.fpt.automatedtesting.subjectclasses.SubjectClass;
import com.fpt.automatedtesting.subjectclasses.SubjectClassRepository;
import com.fpt.automatedtesting.submissions.Submission;
import com.fpt.automatedtesting.submissions.SubmissionRepository;
import com.fpt.automatedtesting.submissions.dtos.response.SubmissionResponse;
import com.fpt.automatedtesting.users.UserRepository;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;


//TODO:Log file lại toàn bộ


@EnableAsync
@Service
public class PracticalExamServiceImpl implements PracticalExamService {
    private static final Logger logger = LogManager.getLogger(PracticalExamServiceImpl.class);


    private static final String PREFIX_EXAM_CODE = "Practical_";

    private final PracticalExamRepository practicalExamRepository;
    private final ScriptRepository scriptRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final SubjectClassRepository subjectClassRepository;
    private final DuplicatedCodeRepository duplicatedCodeRepository;
    private final LecturerRepository lecturerRepository;
    private final SubjectRepository subjectRepository;
    private final DuplicatedCodeService duplicatedCodeService;
    private Queue<StudentSubmissionDto> submissionQueue;
    private final GithubResultService githubResultService;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public PracticalExamServiceImpl(PracticalExamRepository practicalExamRepository, ScriptRepository scriptRepository, SubmissionRepository submissionRepository, UserRepository userRepository, SubjectClassRepository subjectClassRepository, DuplicatedCodeRepository duplicatedCodeRepository, LecturerRepository lecturerRepository, SubjectRepository subjectRepository, DuplicatedCodeService duplicatedCodeService, GithubResultService githubResultService) {
        this.practicalExamRepository = practicalExamRepository;
        this.scriptRepository = scriptRepository;
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.subjectClassRepository = subjectClassRepository;
        this.duplicatedCodeRepository = duplicatedCodeRepository;
        this.lecturerRepository = lecturerRepository;
        this.subjectRepository = subjectRepository;
        this.duplicatedCodeService = duplicatedCodeService;
        this.githubResultService = githubResultService;
        this.submissionQueue = new PriorityQueue<>();
    }

    @Override
    @Transactional
    public String create(PracticalExamRequest dto) {
        List<PracticalExam> saveEntities = null;
        List<Integer> subjectClassesId = dto.getSubjectClasses();
        if (subjectClassesId != null && subjectClassesId.size() > 0) {
            saveEntities = new ArrayList<>();
            for (Integer subjectClassId : subjectClassesId) {
                SubjectClass subjectClass = subjectClassRepository
                        .findByIdAndActiveIsTrue(subjectClassId)
                        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found class for id" + subjectClassId));
                List<Student> studentList = subjectClass.getStudents();

                String practicalExamCode = PREFIX_EXAM_CODE + subjectClass.getSubject().getCode() + "_"
                        + subjectClass.getAClass().getClassCode() + "_" + dto.getDate().replace("-", "");
                File fol = new File(PATH_SUBMISSIONS + File.separator + practicalExamCode);
                if (!fol.exists()) {
                    fol.mkdirs();
                }
                List<Script> scriptEntities = null;
                List<Integer> listScriptId = dto.getListScripts();
                if (listScriptId != null && listScriptId.size() > 0) {
                    scriptEntities = new ArrayList<>();
                    for (Integer id : listScriptId) {
                        Script scriptEntity = scriptRepository.findByIdAndActiveIsTrue(id)
                                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found script for Id: " + id));
                        scriptEntities.add(scriptEntity);
                    }
                }

                PracticalExam practicalExam = MapperManager.map(dto, PracticalExam.class);
                List<Submission> submissionList = new ArrayList<>();
                if (studentList != null && studentList.size() > 0) {
                    for (Student student : studentList) {
                        Submission submission = new Submission();
                        submission.setStudent(student);
                        submission.setPracticalExam(practicalExam);
                        submission.setActive(true);
                        submission.setEvaluatedOnline(false);
                        submission.setScriptCode(getScriptCodeRandom(scriptEntities));
                        submissionList.add(submission);
                    }

                    practicalExam.setScripts(scriptEntities);
                    practicalExam.setSubmissions(submissionList);
                    practicalExam.setCode(practicalExamCode);
                    practicalExam.setState(STATE_NOT_EVALUATE);
                    practicalExam.setSubjectClass(subjectClass);
                    practicalExam.setDate(dto.getDate());
                    practicalExam.setActive(true);
                    saveEntities.add(practicalExam);
                    File file = new File(PathConstants.PATH_SUBMISSIONS + File.separator + practicalExamCode);
                    file.mkdirs();
                }
            }

            List<PracticalExam> result = practicalExamRepository.saveAll(saveEntities);
            if (result == null) {
                return "Create practical exam failed";
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "No student from this class");
        }
        return "Create practical exam successfully";
    }

    @Override
    @Transactional
    public String update(PracticalExamRequest dto) {
        PracticalExam practicalExam = practicalExamRepository.findByIdAndStateEqualsAndActiveIsTrue(dto.getId(), STATE_NOT_EVALUATE)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam for id" + dto.getId()));
        submissionRepository.deleteAllByPracticalExam(practicalExam);

        List<Integer> subjectClassesId = dto.getSubjectClasses();
        if (subjectClassesId != null && subjectClassesId.size() > 0) {
            for (Integer subjectClassId : subjectClassesId) {
                SubjectClass subjectClass = subjectClassRepository
                        .findByIdAndActiveIsTrue(subjectClassId)
                        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found class for id" + subjectClassId));
                List<Student> studentList = subjectClass.getStudents();

                String practicalExamCode = PREFIX_EXAM_CODE + subjectClass.getSubject().getCode() + "_"
                        + subjectClass.getAClass().getClassCode() + "_" + dto.getDate().replace("-", "");

                if (studentList != null && studentList.size() > 0) {
                    List<Script> scriptEntities = null;
                    List<Integer> listScriptId = dto.getListScripts();
                    if (listScriptId != null && listScriptId.size() > 0) {
                        scriptEntities = new ArrayList<>();
                        for (Integer id : listScriptId) {
                            Script scriptEntity = scriptRepository.findByIdAndActiveIsTrue(id)
                                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found script for Id: " + id));
                            scriptEntities.add(scriptEntity);
                        }
                    }

                    List<Submission> submissionList = new ArrayList<>();

                    for (Student student : studentList) {
                        Submission submission = new Submission();
                        submission.setStudent(student);
                        submission.setPracticalExam(practicalExam);
                        submission.setActive(true);
                        submission.setScriptCode(getScriptCodeRandom(scriptEntities));
                        submissionList.add(submission);
                    }

                    practicalExam.setScripts(scriptEntities);
                    practicalExam.setSubmissions(null);
                    practicalExam.setSubmissions(submissionList);
                    practicalExam.setCode(practicalExamCode);
                    practicalExam.setState(STATE_NOT_EVALUATE);
                    practicalExam.setSubjectClass(subjectClass);
                    practicalExam.setDate(dto.getDate());
                    practicalExam.setActive(true);
                }
            }
            PracticalExam result = practicalExamRepository.save(practicalExam);
            if (result == null) {
                return "Update practical exam failed";
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "No student from this class");
        }
        return "Update practical exam successfully";
    }

    @Override
    public Boolean updatePracticalExamResult(PracticalExamResultDto practicalExamResultDto) {
        PracticalExam practicalExam = practicalExamRepository
                .findByIdAndActiveIsTrue(practicalExamResultDto.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam for id" + practicalExamResultDto.getId()));
        List<Submission> submissions = practicalExam.getSubmissions();
        for (Submission entity : submissions) {
            for (SubmissionDetailsDto dto : practicalExamResultDto.getSubmissions()) {
                if (entity.getId() == dto.getId()) {
                    entity.setPoint(dto.getPoint());
                    entity.setSubmitPath(dto.getSubmitPath());
                    entity.setTimeSubmitted(dto.getTimeSubmitted());
                }
            }
        }
        practicalExam.setState(practicalExamResultDto.getState());

        if (practicalExamRepository.saveAndFlush(practicalExam) == null) {
            throw new CustomException(HttpStatus.CONFLICT, "Cannot update practical exam submission with id:" + practicalExamResultDto.getId());
        }
        return false;
    }

    private String getScriptCodeRandom(List<Script> scripts) {
        if (scripts != null && scripts.size() > 0) {
            int index = new Random().nextInt(scripts.size());
            return scripts.get(index).getCode() + "_DE" + String.format("%02d", (index + 1));
        }
        return null;
    }

    @Override
    public void downloadPracticalTemplate(Integer practicalExamId, HttpServletResponse response) {
        PracticalExam practicalExam = practicalExamRepository.
                findByIdAndActiveIsTrue(practicalExamId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam for Id:" + practicalExamId));
        String examCode = practicalExam.getCode();
        int count = 0;
        // Create practical folder
        File practicalFol = new File(PathConstants.PATH_PRACTICAL_EXAMS + File.separator + practicalExam.getCode());
        boolean check = practicalFol.exists();
        if (check) {
            FileManager.deleteFolder(practicalFol);
            File zipFile = new File(practicalFol.getAbsolutePath() + EXTENSION_ZIP);
            if (zipFile.delete()) {
                System.out.println("Deleted");
            } else {
                System.out.println("Failed delete");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        do {
            count++;
            check = practicalFol.mkdir();
            if (check) {
                break;
            }
        } while (count < 20);
        // Create submission folder

        count = 0;
        File submissionFol = new File(practicalFol.getAbsolutePath() + File.separator + "Submissions");
        do {
            count++;
            check = submissionFol.mkdir();
            if (check) {
                break;
            }
        } while (count < 20);
        if (!check) {
            throw new CustomException(HttpStatus.CONFLICT, "[Submission] - Occur error during create file ! Please try later");
        }


        // Create submission folder
        File dbToolsFol = new File(practicalFol.getAbsolutePath() + File.separator + "DBTools");
        count = 0;
        do {
            count++;
            check = dbToolsFol.mkdir();
            if (check) {
                break;
            }
        } while (count < 20);
        if (!check) {
            throw new CustomException(HttpStatus.CONFLICT, "[DBTools] - Occur error ! Please try later");
        }

        // Create submission folder
        File scriptDBFol = new File(practicalFol.getAbsolutePath() + File.separator + "Script_SQL");
        count = 0;
        do {
            count++;
            check = scriptDBFol.mkdir();
            if (check) {
                break;
            }
        } while (count < 20);
        if (!check) {
            throw new CustomException(HttpStatus.CONFLICT, "[Script] - Occur error ! Please try later");
        }

        // Create student template
        File studentTemplateFol = new File(practicalFol.getAbsolutePath() + File.separator + "Student_Template_Project");
        count = 0;
        do {
            count++;
            check = studentTemplateFol.mkdir();
            if (check) {
                break;
            }
        } while (count < 20);
        if (!check) {
            throw new CustomException(HttpStatus.CONFLICT, "[Template] - Occur error ! Please try later");
        }


        List<Student> students = practicalExam.getSubjectClass().getStudents();
        if (students == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "There are no student join this practical exam");
        }

        // Write students details in practical exams to csv
        List<List<String>> rowsStudentsList = new ArrayList<>();
        List<List<String>> rowsStudentsResult = new ArrayList<>();

        rowsStudentsList.add(Arrays.asList(COLUMN_NO, COLUMN_STUDENT_CODE, COLUMN_STUDENT_NAME, COLUMN_SCRIPT_CODE,
                COLUMN_SUBMITTED_TIME, COLUMN_EVALUATED_TIME, COLUMN_CODING_CONVENTION, COLUMN_RESULT,
                COLUMN_TOTAL_POINT, COLUMN_ERROR));
        rowsStudentsResult.add(Arrays.asList(COLUMN_NO, COLUMN_STUDENT_CODE, COLUMN_STUDENT_NAME, COLUMN_SCRIPT_CODE,
                COLUMN_TOTAL_POINT));

        List<Submission> submissionList = practicalExam.getSubmissions();
        if (submissionList != null && submissionList.size() > 0) {
            List<SubmissionReport> list = MapperManager.mapAll(submissionList, SubmissionReport.class);
            for (int i = 0; i < list.size(); i++) {
                SubmissionReport submission = list.get(i);
                StudentReportDto student = submission.getStudent();
                String fullName = student.getLastName();
                String middleName = student.getMiddleName();
                if (middleName != null && !middleName.equals("")) {
                    fullName += " " + student.getMiddleName();
                }
                fullName += " " + student.getFirstName();
                rowsStudentsList.add(Arrays.asList(String.valueOf(i + 1), student.getCode().trim(), fullName, submission.getScriptCode().trim()));
                rowsStudentsResult.add(Arrays.asList(String.valueOf(i + 1), student.getCode().trim(), fullName));
            }
        }
        //copy source to target using Files Class
        try {

            writeDataToCSVFile(practicalFol.getAbsolutePath() + File.separator + "Student_List.csv", rowsStudentsList);
            writeDataToCSVFile(practicalFol.getAbsolutePath() + File.separator + "Student_Results.csv", rowsStudentsResult);

            // Copy script files
            File scriptFol = new File(practicalFol.getAbsolutePath() + File.separator + "TestScripts");
            File docsFol = new File(practicalFol.getAbsolutePath() + File.separator + "ExamDocuments");
            count = 0;
            boolean checkScriptFolCreated = false;
            do {
                count++;
                checkScriptFolCreated = scriptFol.mkdir();
                if (checkScriptFolCreated) {
                    break;
                }
            } while (count < 20);

            if (!checkScriptFolCreated) {
                throw new CustomException(HttpStatus.CONFLICT, "[Scripts] - Occur error ! Please try later");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean checkDocFolCreate = false;
            count = 0;
            do {
                count++;
                checkDocFolCreate = docsFol.mkdir();
                if (checkDocFolCreate) {
                    break;
                }
            } while (count < 20);

            if (!checkDocFolCreate) {
                throw new CustomException(HttpStatus.CONFLICT, "[Document] - Occur error ! Please try later");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String pathScript = "";
            String pathDocs = "";
            String pathServer = "";
            String extension = "";
            String pathTemplateQuestion = "";
            String templateProjectStudentFol = "";
            String pathTemplateProject = "";
            String databasePath = "";
            String testDataPath = "";
            String subjectCode = "";
            if (examCode.contains(CODE_SUBJECT_JAVA_WEB)) {
                pathScript = PATH_SCRIPT_JAVA_WEB;
                pathDocs = PATH_DOCS_JAVA_WEB;
                pathServer = PATH_SERVER_JAVA_WEB;
                extension = EXTENSION_JAVA;
                pathTemplateQuestion = PATH_TEMPLATE_QUESTION_JAVA_WEB;
                templateProjectStudentFol = PATH_STUDENT_JAVA;
                pathTemplateProject = PATH_TEMPLATE_PROJECT + File.separator + "JavaWebTemplate";
                databasePath = PATH_DATABASE_SCRIPT_JAVA_WEB;
                testDataPath = PATH_TESTDATA_JAVA_WEB;
                subjectCode = CODE_SUBJECT_JAVA_WEB;
            } else if (examCode.contains(CODE_SUBJECT_JAVA)) {
                pathScript = PATH_SCRIPT_JAVA;
                pathDocs = PATH_DOCS_JAVA;
                pathServer = PATH_SERVER_JAVA;
                extension = EXTENSION_JAVA;
                pathTemplateQuestion = PATH_TEMPLATE_QUESTION_JAVA;
                templateProjectStudentFol = PATH_STUDENT_JAVA_WEB;
                pathTemplateProject = PATH_TEMPLATE_PROJECT + File.separator + "JavaTemplate";
                databasePath = PATH_DATABASE_SCRIPT_JAVA;
                testDataPath = PATH_TESTDATA_JAVA;
                subjectCode = CODE_SUBJECT_JAVA;

            } else if (examCode.contains(CODE_SUBJECT_C)) {
                pathScript = PATH_SCRIPT_C;
                pathDocs = PATH_DOCS_C;
                pathServer = PATH_SERVER_C;
                extension = EXTENSION_C;
                pathTemplateQuestion = PATH_TEMPLATE_QUESTION_C;
                templateProjectStudentFol = PATH_STUDENT_C;
                pathTemplateProject = PATH_TEMPLATE_PROJECT + File.separator + "CTemplate";
                databasePath = PATH_DATABASE_SCRIPT_C;
                testDataPath = PATH_TESTDATA_C;
                subjectCode = CODE_SUBJECT_C;

            } else if (examCode.contains(CODE_SUBJECT_CSHARP)) {
                pathScript = PATH_SCRIPT_CSHARP;
                pathDocs = PATH_DOCS_CSHARP;
                pathServer = PATH_SERVER_CSHARP;
                extension = EXTENSION_CSHARP;
                pathTemplateQuestion = PATH_TEMPLATE_QUESTION_C_SHARP;
                templateProjectStudentFol = PATH_STUDENT_CSHARP;
                pathTemplateProject = PATH_TEMPLATE_PROJECT + File.separator + "CSharpTemplate";
                databasePath = PATH_DATABASE_SCRIPT_C_SHARP;
                testDataPath = PATH_TESTDATA_C_SHARP;
                subjectCode = CODE_SUBJECT_CSHARP;

            }
            // loop by list script test đã assign
            List<Script> scripts = practicalExam.getScripts();
            if (scripts != null) {
                for (Script script : practicalExam.getScripts()) {
                    // For Test Scripts
                    File sourceScriptPath = new File(pathScript + script.getCode() + extension);
                    File targetScriptPath = new File(scriptFol.getAbsolutePath() + File.separator + script.getCode() + extension);
                    if (sourceScriptPath.exists()) {
                        Files.copy(sourceScriptPath.toPath(), targetScriptPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        System.out.println("[" + examCode + "]- Passed - Test Script" + ": " + script);
                    }

                    // For docs
                    File sourceDocPath = new File(pathDocs + script.getCode() + EXTENSION_DOCX);
                    File targetDocPath = new File(docsFol.getAbsolutePath() + File.separator + script.getCode() + EXTENSION_DOCX);
                    if (sourceDocPath.exists()) {
                        Files.copy(sourceDocPath.toPath(), targetDocPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        examCode = PREFIX_EXAM_CODE + script.getSubject().getCode();
                    } else {
                        System.out.println("[" + examCode + "]- Passed - Exam document" + ": " + script);
                    }

                    // Copy DBTools - Connection
                    try {
                        File offlineDBTool = new File(PATH_DB_TOOLS + File.separator + script.getCode() + "_Offline" + extension);
                        File dbPath = new File(dbToolsFol.getAbsolutePath() + File.separator + "DBUtilities_" + script.getCode() + extension);
                        if (offlineDBTool.exists()) {
                            Files.copy(offlineDBTool.toPath(),
                                    dbPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } else {
                            System.out.println("[" + examCode + "]- Passed - Exam DBTools" + ": " + script);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Copy DB Script SQL
                    try {
                        File databaseScriptPath = new File(databasePath + script.getCode() + EXTENSION_SQL);
                        if (databaseScriptPath.exists()) {
                            File dbScript = new File(scriptDBFol.getAbsolutePath() + File.separator + script.getCode() + EXTENSION_SQL);
                            Files.copy(databaseScriptPath.toPath(),
                                    dbScript.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    // Copy Test data if existed
                    File testDataFile = new File(testDataPath + script.getCode() + EXTENSION_TXT);
                    File storedFile = new File(dbToolsFol.getAbsolutePath() + File.separator + script.getCode() + EXTENSION_TXT);
                    System.out.println(storedFile.getAbsolutePath());
                    if (testDataFile.exists()) {
                        try {
                            Files.copy(testDataFile.toPath(),
                                    storedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    // Set up project template question
                    try {
                        File templateFile = new File(pathTemplateQuestion + File.separator + script.getCode() + extension);
                        File studentTemplate = new File(templateProjectStudentFol + File.separator + "TemplateQuestion" + extension);
                        Files.copy(templateFile.toPath(),
                                studentTemplate.toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                        String zipFile = studentTemplateFol.getAbsolutePath() + File.separator + script.getCode() + EXTENSION_ZIP;
                        FileManager.zipFolder(pathTemplateProject,
                                pathTemplateProject);
                        Files.copy(Paths.get(pathTemplateProject + EXTENSION_ZIP),
                                Paths.get(zipFile),
                                StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                //copy server
                File sourceServerPath = new File(pathServer);
                File targetServerPath = new File(practicalFol.getAbsolutePath() + File.separator + "Server");

                if (sourceServerPath.exists()) {
                    FileUtils.copyDirectory(sourceServerPath, targetServerPath);
                }

                // Make info json files
                ObjectMapper objectMapper = new ObjectMapper();
                PracticalInfo practicalInfo = new PracticalInfo();
                practicalInfo.setName(practicalExam.getCode());
                practicalInfo.setExamCode(examCode);
                practicalInfo.setSubjectCode(subjectCode);
                objectMapper.writeValue(
                        new FileOutputStream(practicalFol.getAbsoluteFile() +
                                File.separator
                                + PRACTICAL_INFO_FILE_NAME),
                        practicalInfo);
            }

            // Zip folder
            FileManager.zipFolder(practicalFol.getAbsolutePath(), practicalFol.getAbsolutePath());
            downloadTemplate(response, practicalExam.getCode());
        } catch (Exception e) {
            e.printStackTrace();

            throw new CustomException(HttpStatus.CONFLICT, "Cannot download practical exam ! Please try later");
        }
    }


    @Override
    public String delete(Integer id) {
        PracticalExam entity = practicalExamRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Practical exam is not found with Id " + id));
        entity.setActive(false);
        PracticalExam result = practicalExamRepository.save(entity);
        if (result == null) {
            throw new CustomException(HttpStatus.CONFLICT, "Delete practical exam failed ! Please try later");
        }
        return "Delete practical exam successfully";
    }


    private void writeDataToCSVFile(String filePath, List<List<String>> data) {
        try {
            OutputStream os = new FileOutputStream(filePath);
            os.write(239);
            os.write(187);
            os.write(191);
            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
            String s = "";
            for (List<String> rowData : data) {
                s += String.join(",", rowData) + "\n";
            }
            w.print(s);
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(HttpStatus.CONFLICT, "Cannot download practical exam ! Please try later");
        }
    }

    @Override
    public List<StudentSubmissionDetails> getListStudentInPracticalExam(Integer id) {

        List<StudentSubmissionDetails> result = null;
        PracticalExam practicalExamEntity = practicalExamRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam for Id" + id));
        List<Submission> submissionList = submissionRepository.findAllByPracticalExamAndPracticalExam_ActiveAndActiveIsTrue(practicalExamEntity, true);
        if (submissionList != null && submissionList.size() > 0) {
            result = new ArrayList<>();
            for (Submission submission : submissionList) {
                StudentSubmissionDetails dto = new StudentSubmissionDetails();
                Student student = submission.getStudent();
                if (student == null)
                    throw new CustomException(HttpStatus.NOT_FOUND, "Not found Student");
                dto.setId(submission.getId());
                dto.setStudentCode(student.getCode());
                String fullName = student.getLastName();
                String middleName = student.getMiddleName();
                if (middleName != null && !middleName.equals("")) {
                    fullName += " " + student.getMiddleName();
                }
                fullName += " " + student.getFirstName();
                dto.setStudentName(fullName);
                dto.setScriptCode(submission.getScriptCode());
                result.add(dto);
            }
        }
        return result;
    }

    @Override
    public List<PracticalExamResponse> getPracticalExamsOfSubject(Integer id) {

        Subject subject = subjectRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found subject for Id:" + id));
        List<PracticalExamResponse> result = null;
        List<SubjectClass> subjectClassList = subject.getSubjectClasses();

        if (subjectClassList != null && subjectClassList.size() > 0) {
            result = new ArrayList<>();

            List<Integer> subjectClassId = new ArrayList<>();
            for (SubjectClass subjectClass : subjectClassList) {
                subjectClassId.add(subjectClass.getId());
                List<PracticalExam> practicalExams = subjectClass.getPracticalExams();
                if (practicalExams != null && practicalExams.size() > 0) {
                    result = MapperManager.mapAll(practicalExams, PracticalExamResponse.class);
                    if (result != null) {
                        for (PracticalExamResponse practicalExamDto : result) {
                            practicalExamDto.setSubjectCode(subject.getCode());
                            practicalExamDto.setSubjectId(subject.getId());
                            practicalExamDto.setClassCode(subjectClass.getAClass().getClassCode());
                        }
                    }
                }
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found subject class for lecturer");
        }
        return result;
    }

    @Override
    public List<PracticalExamResponse> enrollPracticalExam(String code) {
        Lecturer lecturer = lecturerRepository.findByCodeAndActiveIsTrue(code);
        if (lecturer == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found lecturer for code " + code);
        }

        List<PracticalExamResponse> result = null;
        List<SubjectClass> subjectClassList = lecturer.getSubjectClasses();
        if (subjectClassList != null && subjectClassList.size() > 0) {
            result = new ArrayList<>();
            for (SubjectClass subjectClass : subjectClassList) {
                List<PracticalExam> practicalExams = subjectClass.getPracticalExams();
                if (practicalExams != null && practicalExams.size() > 0) {
                    List<PracticalExamResponse> list = MapperManager.mapAll(practicalExams, PracticalExamResponse.class);
                    if (list != null) {
                        for (PracticalExamResponse practicalExamDto : list) {
                            practicalExamDto.setSubjectCode(subjectClass.getSubject().getCode());
                            result.add(practicalExamDto);
                        }
                    }
                }
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found subject class for lecturer");
        }
        return result;
    }

    @Override
    public List<PracticalExamResponse> getPracticalExamsOfLecturer(Integer id) {
        Lecturer lecturer = lecturerRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found lecturer for Id:" + id));

        List<PracticalExamResponse> result = null;
        List<SubjectClass> subjectClassList = lecturer.getSubjectClasses();
        if (subjectClassList != null && subjectClassList.size() > 0) {
            result = new ArrayList<>();
            for (SubjectClass subjectClass : subjectClassList) {
                List<PracticalExam> practicalExams = subjectClass.getPracticalExams();
                List<PracticalExamResponse> tempList = new ArrayList<>();
                if (practicalExams != null && practicalExams.size() > 0) {
                    tempList = MapperManager.mapAll(practicalExams, PracticalExamResponse.class);
                    if (tempList != null) {
                        for (PracticalExamResponse practicalExamDto : tempList) {
                            practicalExamDto.setClassCode(subjectClass.getAClass().getClassCode());
                            practicalExamDto.setSubjectCode(subjectClass.getSubject().getCode());
                            result.add(practicalExamDto);
                        }
                    }
                }
            }
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found subject class for lecturer");
        }
        return result;
    }


    @Override
    public String checkDuplicatedCode(PracticalInfo info) {
        applicationEventPublisher.publishEvent(info);
        return "Start checking duplicated code successfully !";
    }

    @Async
    @EventListener
    public void processChecking(PracticalInfo info) {
        PracticalExam practicalExam = practicalExamRepository.findByCodeAndActiveIsTrue(info.getExamCode())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found id for Id:" + info.getExamCode()));
        Map<String, List<String>> methods = new HashMap<>();
        List<String> allStudentSubmissionFileName = new ArrayList<>();
        String sourcePath = PATH_SUBMISSIONS + File.separator +
                info.getExamCode() + File.separator + "Sources";
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(
                    Paths.get(sourcePath));
            for (Path path : directoryStream) {
                allStudentSubmissionFileName.add(path.getFileName().toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String extension = "";
        if (info.getExamCode().contains(CODE_PRACTICAL_JAVA) || info.getExamCode().contains(CODE_PRACTICAL_JAVA)) {
            extension = ".java";
        } else if (info.getExamCode().contains(CODE_PRACTICAL_CSHARP)) {
            extension = ".cs";
        } else if (info.getExamCode().contains(CODE_PRACTICAL_C)) {
            extension = ".c";
        }

        // Declare duplicated code details
        List<DuplicatedCodeDto> duplicatedCodeDtoList = new ArrayList<>();

        for (String studentCode : allStudentSubmissionFileName) {

            Map<String, List<String>> methodForGitHub = new HashMap<>();
            List<File> studentFiles = new ArrayList<>();
            FileManager.getAllFiles(sourcePath + File.separator + studentCode, studentFiles, extension);

            DuplicatedCodeDto dto = new DuplicatedCodeDto();
            List<FileVectors> studentFileVectors = new ArrayList<>();
            dto.setStudentCode(studentCode);

            if (!studentFiles.isEmpty()) {
                for (File studentFile : studentFiles) {
                    if (!studentFile.getName().contains("TemplateQuestion")) {

                        Map<String, List<Double>> vectors = new HashMap<>();
                        FileVectors fileVectors = new FileVectors();

                        //TODO: For extend later
//                    File file = checkValidFile(studentFile);

                        String filePath = studentFile.getAbsolutePath();
                        String prefixName = info.getExamCode() + "_" + studentCode + "_" + studentFile.getName();
                        try {
                            Files.copy(Paths.get(filePath),
                                    Paths.get(PATH_SERVER_REPOSITORY + File.separator + prefixName),
                                    StandardCopyOption.REPLACE_EXISTING
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        List<String> studentMethods = new ArrayList<>();

                        // Lấy vectors của file A
                        duplicatedCodeService.getListTree(filePath, CODE_PRACTICAL_JAVA, studentCode + "_" + studentFile.getName(), vectors, studentMethods);

                        fileVectors.setMethodVectors(vectors);
                        fileVectors.setFileName(studentFile.getName());
                        studentFileVectors.add(fileVectors);
                        // Methods String for check online
                        methods.put(prefixName, studentMethods);
                        methodForGitHub.put(prefixName, studentMethods);
                    }
                }
                dto.setStudentFileVectors(studentFileVectors);
            }
            duplicatedCodeDtoList.add(dto);
            Map<String, List<GitHubFileDuplicateDTO>> listDuplicate = getGithubResult(methodForGitHub, extension);
            githubResultService.create(practicalExam.getId(), studentCode, listDuplicate);
        }
        processStudentDuplicatedCode(duplicatedCodeDtoList, practicalExam);
    }

    private Map<String, List<GitHubFileDuplicateDTO>> getGithubResult(Map<String, List<String>> methods, String extension) {

        Map<String, List<GitHubFileDuplicateDTO>> listDuplicate = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : methods.entrySet()) {
            Map<String, GitHubFileDuplicateDTO> listData = new HashMap<>();
            System.out.println("-----------");
            System.out.println("Student code - File :" + entry.getKey());
            int fileLength = 0;
            for (String s : entry.getValue()) {
                String convertedString = PracticalExamUtils.removeNullOrBlankElements(s);
                fileLength += convertedString.length();
                Map<String, GitHubFileDuplicateDTO> result = PracticalExamUtils.checkDuplicatedCodeGithub(convertedString, extension);
                for (Map.Entry<String, GitHubFileDuplicateDTO> item : result.entrySet()) {
                    if (listData.containsKey(item.getKey())) {
                        GitHubFileDuplicateDTO dto = listData.get(item.getKey());
                        int currentScore = dto.getScore();
                        currentScore += item.getValue().getScore();
                        dto.setScore(currentScore);
                    } else {
                        listData.put(item.getKey(), item.getValue());
                    }
                }
                String a = "";
            }
            System.out.println("Student code - File :" + entry.getKey());
            ArrayList<GitHubFileDuplicateDTO> listGithubFile = sortSimilarFileByScore(listData, fileLength);
            // return a map fileName - list githubfile
            listDuplicate.put(entry.getKey(), listGithubFile);
        }
        return listDuplicate;
    }


    private ArrayList<GitHubFileDuplicateDTO> sortSimilarFileByScore(Map<String, GitHubFileDuplicateDTO> listData, int fileLength) {
        System.out.println("===================SORT=========================");
        Comparator<Map.Entry<String, GitHubFileDuplicateDTO>> valueComparator = new Comparator<Map.Entry<String, GitHubFileDuplicateDTO>>() {
            @Override
            public int compare(Map.Entry<String, GitHubFileDuplicateDTO> first, Map.Entry<String, GitHubFileDuplicateDTO> second) {
                double firstScore = first.getValue().getScore();
                double secondScore = second.getValue().getScore();
                if (firstScore < secondScore) return 1;
                else if (secondScore < firstScore) return -1;
                else return 0;
            }
        };
        List<Entry<String, GitHubFileDuplicateDTO>> listOfEntries = new ArrayList<Entry<String, GitHubFileDuplicateDTO>>(listData.entrySet());
        Collections.sort(listOfEntries, valueComparator);
        ArrayList listFile = new ArrayList<GitHubFileDuplicateDTO>();
        int count = 0;
        for (Entry<String, GitHubFileDuplicateDTO> entry : listOfEntries) {
            int score = entry.getValue().getScore();
            double percent = (double) score / fileLength * 100;
            entry.getValue().setPercent(percent);
            if (percent >= MINIMUM_SIMILAR_FILE) listFile.add(entry.getValue());
        }
        return listFile;
    }

    private void processStudentDuplicatedCode(List<DuplicatedCodeDto> duplicatedCodeDtoList, PracticalExam practicalExam) {
        List<String> tokenChecked = new ArrayList<>();
        Map<String, Double> similarityMethods = new HashMap<>();
        for (DuplicatedCodeDto dto : duplicatedCodeDtoList) {
            List<FileVectors> listFileVector = dto.getStudentFileVectors();
            for (FileVectors fileVectors : listFileVector) {
                Map<String, List<Double>> methodVectors = fileVectors.getMethodVectors();
                for (Map.Entry<String, List<Double>> entry : methodVectors.entrySet()) {
                    computeMaxSimilarityBetweenMethods(dto.getStudentCode(),
                            entry.getKey(),
                            entry.getValue(),
                            similarityMethods,
                            duplicatedCodeDtoList,
                            tokenChecked);
                }
            }
        }
        insertDuplicatedCode(similarityMethods, practicalExam);
    }


    private Map<String, List<Double>> computeMaxSimilarityBetweenMethods(String firstStudentCode, String firstFileToken,
                                                                         List<Double> firstMethodVector, Map<String, Double> similarityMethods,
                                                                         List<DuplicatedCodeDto> duplicatedCodeDtoList, List<String> tokenChecked) {
        for (DuplicatedCodeDto dto : duplicatedCodeDtoList) {
            String secondStudentCode = dto.getStudentCode();
            if (!secondStudentCode.equalsIgnoreCase(firstStudentCode)) {
                List<FileVectors> listFileVector = dto.getStudentFileVectors();
                for (FileVectors fileVectors : listFileVector) {
                    Map<String, List<Double>> methodVectors = fileVectors.getMethodVectors();
                    double maxMethodSimilarityPercent = 0;
                    String token = "";
                    for (Map.Entry<String, List<Double>> entry : methodVectors.entrySet()) {
                        String secondFileToken = entry.getKey();
                        String pairToken = firstFileToken + "~" + secondFileToken;
                        String pairTokenSwap = secondFileToken + "~" + firstFileToken;
                        if (!tokenChecked.contains(pairToken) && !tokenChecked.contains(pairTokenSwap)) {
                            tokenChecked.add(pairToken);
                            List<Double> secondMethodVector = entry.getValue();
                            double similarityPercent = CosineSimilarity.computeSimilarity(firstMethodVector, secondMethodVector);
                            if (similarityPercent > maxMethodSimilarityPercent) {
                                maxMethodSimilarityPercent = similarityPercent;
                                token = pairToken;
                            }
                        }
                    }
                    if (!token.equals("") && maxMethodSimilarityPercent >= 0.3) {
                        similarityMethods.put(token, maxMethodSimilarityPercent);
                    } else {
                        similarityMethods.put(token, 0.0);
                    }
                }
            }
        }
        return null;
    }

    private void insertDuplicatedCode(Map<String, Double> checkedTokens, PracticalExam practicalExam) {
        Map<String, Double> result = new HashMap<>();
        Map<String, List<Double>> studentSimilarityPercentMap = new HashMap<>();
        if (checkedTokens != null && checkedTokens.size() > 0) {
            for (Map.Entry<String, Double> entry : checkedTokens.entrySet()) {
                String firstStudentCode = "";
                String secondStudentCode = "";
                String key = entry.getKey();
                String[] arr = key.split("~");
                if (arr != null && arr.length >= 2) {
                    String[] firstStudent = arr[0].split("_");
                    String[] secondStudent = arr[1].split("_");
                    if (firstStudent != null && secondStudent != null
                            && firstStudent.length > 0 && secondStudent.length > 0) {
                        firstStudentCode = firstStudent[0];
                        secondStudentCode = secondStudent[0];
                    }
                }
                if (!firstStudentCode.equals("") && !secondStudentCode.equals("")) {
                    String studentsToken = firstStudentCode + "_" + secondStudentCode;
                    Double value = entry.getValue();
                    List<Double> list = studentSimilarityPercentMap.get(studentsToken);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(value);
                    studentSimilarityPercentMap.put(studentsToken, list);
                }
            }
        }

        for (Map.Entry<String, List<Double>> entry : studentSimilarityPercentMap.entrySet()) {
            String studentsToken = entry.getKey();
            List<Double> similarityPercentList = entry.getValue();
            double summaryPercent = 0;
            if (similarityPercentList != null && similarityPercentList.size() > 0) {
                for (Double value : similarityPercentList) {
                    summaryPercent += value;
                }
                result.put(studentsToken, summaryPercent * 100 / similarityPercentList.size());
            }
        }
        List<DuplicatedCode> duplicatedCodes = new ArrayList<>();
        for (Map.Entry<String, Double> entry : result.entrySet()) {
            String studentsToken = entry.getKey();
            Double similarityPercent = entry.getValue();
            String[] arr = studentsToken.split("_");
            DuplicatedCode duplicatedCode = new DuplicatedCode();
            duplicatedCode.setPracticalExam(practicalExam);
            duplicatedCode.setStudentsToken(studentsToken);
            duplicatedCode.setSimilarityPercent(similarityPercent);
            DuplicatedCode responseEntity = duplicatedCodeRepository.save(duplicatedCode);
            if (responseEntity != null) {
                if (checkedTokens != null && checkedTokens.size() > 0) {
                    String firstStudent = arr[0];
                    String secondStudent = arr[1];
                    List<DuplicatedCodeDetails> list = new ArrayList<>();
                    for (Map.Entry<String, Double> entryToken : checkedTokens.entrySet()) {
                        Double value = entryToken.getValue();
                        if (value > 0.45) {
                            String filesToken = entryToken.getKey();
                            if (filesToken.contains(firstStudent) && filesToken.contains(secondStudent)) {
                                DuplicatedCodeDetails details = new DuplicatedCodeDetails();
                                details.setDuplicatedCode(responseEntity);
                                details.setFilesToken(filesToken + " : " + value);
                                list.add(details);
                            }
                            duplicatedCode.setDuplicatedCodeDetails(list);
                        }
                    }
                    responseEntity.setDuplicatedCodeDetails(list);
                    DuplicatedCode check = duplicatedCodeRepository.saveAndFlush(responseEntity);
                    if (check != null) {
                        System.out.println("ok");
                    }
                }
            }
            duplicatedCodes.add(duplicatedCode);
        }
        // Process evaluate online
//        processEvaluateOnline(practicalExam);
    }


    @Override
    public List<DuplicatedCodeResponse> getDuplicatedResult(DuplicatedCodeRequest request) {
        List<DuplicatedCodeResponse> result = null;
        List<DuplicatedCode> responseEntities = duplicatedCodeRepository
                .findByStudentsTokenContainingAndPracticalExam_Code(request.getStudentCode(), request.getPracticalExamCode());
        if (responseEntities != null && responseEntities.size() > 0) {
            result = new ArrayList<>();
            for (DuplicatedCode entity : responseEntities) {
                DuplicatedCodeResponse dto = new DuplicatedCodeResponse();
                dto.setStudentsToken(entity.getStudentsToken());
                dto.setSimilarityPercent(entity.getSimilarityPercent());
                List<DuplicatedCodeDetails> duplicatedCodeDetails = entity.getDuplicatedCodeDetails();
                if (duplicatedCodeDetails != null && duplicatedCodeDetails.size() > 0) {
                    List<String> filesTokens = new ArrayList<>();
                    for (DuplicatedCodeDetails details : duplicatedCodeDetails) {
                        filesTokens.add(details.getFilesToken());
                    }
                    dto.setDuplicatedCodeDetails(filesTokens);
                }
                result.add(dto);
            }
        }
        return result;
    }


    @Override
    public List<OnlineTestResult> getResultFromAzure(Integer id) {

        PracticalExam practicalExam = practicalExamRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found id for Id:" + id));
        List<OnlineTestResult> results = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String examCode = practicalExam.getCode();
        String azureProject = "";
        if (examCode.contains(CODE_PRACTICAL_JAVA)) {
            azureProject = AZURE_PROJECT_JAVA;
        } else if (examCode.contains(CODE_PRACTICAL_JAVA_WEB)) {
            azureProject = AZURE_PROJECT_JAVA_WEB;
        } else if (examCode.contains(CODE_PRACTICAL_C)) {
            azureProject = AZURE_PROJECT_C;
        } else if (examCode.contains(CODE_PRACTICAL_CSHARP)) {
            azureProject = AZURE_PROJECT_CSHARP;
        }

        List<Submission> submissions = practicalExam.getSubmissions();
        if (submissions != null && submissions.size() > 0) {
            results = new ArrayList<>();
            List<SubmissionResponse> submissionResponses = MapperManager.mapAll(submissions, SubmissionResponse.class);
            if (submissionResponses != null && submissionResponses.size() > 0) {

                for (SubmissionResponse dto : submissionResponses) {
                    //Getting current date
                    String evaluatedDate = dto.getDate();

                    //Specifying date format that matches the given date
                    Calendar c = Calendar.getInstance();
                    try {
                        //Setting the date to the given date
                        c.setTime(sdf.parse(evaluatedDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    c.add(Calendar.DAY_OF_MONTH, 6);
                    String next6Date = sdf.format(c.getTime());
                    String studentCode = dto.getStudent().getCode();
                    String brandName = PREFIX_BRANCH + examCode + "/" + studentCode;
                    String url = "https://dev.azure.com/" +
                            azureProject +
                            "_apis/test/Runs?branchName=" +
                            "refs/heads/" + brandName +
                            "&minLastUpdatedDate=" + evaluatedDate +
                            "&maxLastUpdatedDate=" + next6Date;
                    String testRunResponse = CustomUtils.sendRequest(url, "");
                    List<AzureTestResult> azureTestResults = null;
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode root = null;
                        root = mapper.readTree(testRunResponse);
                        String value = root.findPath("value").toString();
                        RunTestDto[] runTestArr = mapper.readValue(value, RunTestDto[].class);
                        if (runTestArr != null && runTestArr.length > 0) {
                            azureTestResults = new ArrayList<>();
                            for (int i = 0; i < runTestArr.length; i++) {
                                String testResultResponse = CustomUtils.sendRequest(runTestArr[i].getUrl() + "/results", "");
                                JsonNode testResultNode = mapper.readTree(testResultResponse);
                                String testResultValue = testResultNode.findPath("value").toString();
                                TestRunResult[] arr = mapper.readValue(testResultValue, TestRunResult[].class);
                                if (arr != null && arr.length > 0) {
                                    azureTestResults.add(new AzureTestResult(arr[0].getStartedDate(), arr));
                                }
                            }
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return results;
    }

    @Override
    public String checkOnline(Integer id) {
        PracticalExam practicalExam = practicalExamRepository.findByCodeAndActiveIsTrue("Practical_JavaWeb_SE9999_20200417")
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found id for Id:"));
//        processEvaluateOnline(practicalExam);
        return "Request evaluate successfully";
    }

    @Override
    public String getStudentSubmission(StudentSubmissionDto dto) {

        MultipartFile file = dto.getFile();
        String copyLocation = PATH_SUBMISSIONS + File.separator +
                dto.getExamCode();
        File fol = new File(copyLocation);
        if (!fol.exists()) {
            fol.mkdirs();
        }
        Path filePath = Paths.get(copyLocation + File.separator
                + StringUtils.cleanPath(file.getOriginalFilename()));
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Student student = new Student();
            student.setCode(dto.getStudentCode());
            // Process push to Github repository
            Submission submission = submissionRepository
                    .findByStudent_CodeAndPracticalExam_CodeAndActiveIsTrue(dto.getStudentCode(), dto.getExamCode())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found id for Id:"));

            processEvaluateOnline(dto.getExamCode(), submission);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Successfully";
    }

    private void processEvaluateOnline(String examCode, Submission submission) {

        Date date = new Date();
        String curTime = CustomUtils.getCurDateTime(date, "");
        String scriptCode = submission.getScriptCode();
        String studentCode = submission.getStudent().getCode();
        if (!submission.getEvaluatedOnline() &&
                // Process Git Repository
                processGitRepo(examCode, scriptCode, studentCode)) {
            submission.setDate(curTime);
            submission.setEvaluatedOnline(true);
        }
        if (submissionRepository.save(submission) == null) {
//            TODO:Log file
        }
    }

    private boolean processGitRepo(String examCode, String scriptCode, String studentCode) {
        boolean check = false;
        String testScriptName = "";
        String scriptFormatted = scriptCode.substring(0, scriptCode.lastIndexOf("_"));


        String pathServer = "";
        String pathConnection = "";
        String pathScriptOnline = "";
        String pathOnlineTestFol = "";
        String pathDBOnline = "";
        String pathStudentFol = "";
        String extension = "";
        if (examCode.contains(CODE_PRACTICAL_JAVA_WEB)) {
            pathServer = PATH_SERVER_ONLINE_JAVA_WEB;
            pathDBOnline = PATH_DB_TOOLS + File.separator + scriptFormatted + "_Online" + EXTENSION_JAVA;
            pathConnection = PATH_SERVER_ONLINE_JAVA_WEB_CONNECTION + File.separator + DB_NAME_JAVA;
            pathStudentFol = PATH_SERVER_ONLINE_JAVA_WEB_STUDENT;
            extension = EXTENSION_JAVA;

        } else if (examCode.contains(CODE_PRACTICAL_JAVA)) {
            pathServer = PATH_SERVER_ONLINE_JAVA;
            pathConnection = "";
            pathScriptOnline = PATH_SCRIPT_JAVA + File.separator + scriptFormatted + EXTENSION_JAVA;
            pathOnlineTestFol = PATH_SERVER_ONLINE_JAVA_TEST + File.separator;
            pathStudentFol = PATH_SERVER_ONLINE_JAVA_STUDENT;
            extension = EXTENSION_JAVA;

        } else if (examCode.contains(CODE_PRACTICAL_C)) {
            pathServer = PATH_SERVER_ONLINE_C;
            pathScriptOnline = PATH_SCRIPT_C + scriptFormatted + EXTENSION_C;
            extension = EXTENSION_C;
            pathStudentFol = PATH_SERVER_ONLINE_C_STUDENT;

        } else if (examCode.contains(CODE_PRACTICAL_CSHARP)) {
            pathServer = PATH_SERVER_ONLINE_CSHARP;
            pathConnection = "";
            pathScriptOnline = PATH_SCRIPT_CSHARP + scriptFormatted + EXTENSION_CSHARP;
            pathDBOnline += EXTENSION_CSHARP;
            extension = EXTENSION_CSHARP;
            pathStudentFol = PATH_SERVER_ONLINE_CSHARP_STUDENT;

        }

        // Credentials
        // TODO: Get from DB later
        String name = "headlecturer2020";
        String password = "Capstone12345678";
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(name, password);
        File dir = new File(pathServer);

        try {
//            Git git = Git.open(dir);
////             Check out to default server branch
//            CheckoutCommand checkoutServer = git.checkout();
//            checkoutServer.setName("master");
//            checkoutServer.call();
//
//            // Create new branch base on student code
//            String brandName = PREFIX_BRANCH + examCode + "/" + studentCode;
//
//            try {
//                CreateBranchCommand branchCommand = git.branchCreate();
//                branchCommand.setName(brandName);
//                branchCommand.call();
//            } catch (Exception e) {
//                System.out.println("Brand existed");
//            }
//
//
//            // Check out to that branch and add new file
//            CheckoutCommand checkout = git.checkout();
//            checkout.setName(brandName);
//            checkout.call();

            // Set up copy submission files
            prepareStudentSubmission(studentCode, examCode, pathServer, pathDBOnline, pathConnection,
                    pathScriptOnline, pathOnlineTestFol,pathStudentFol, extension);

//            AddCommand ac = git.add();
//            ac.addFilepattern(".");
//            ac.call();
//
//            // commit
//            CommitCommand commit = git.commit();
//            commit.setCommitter(brandName, brandName)
//                    .setMessage(brandName);
//            commit.call();
//
//            // push
//            PushCommand pc = git.push();
//            pc.setCredentialsProvider(cp)
//                    .setForce(true)
//                    .setPushAll();
//            pc.call().iterator();
//
//            FileManager.deleteFolder(PATH_SERVER_ONLINE_JAVA_WEB + File.separator + "Server");
//
//            // Check out to default server branch
//            CheckoutCommand finalCheckOut = git.checkout();
//            finalCheckOut.setName("master");
//            finalCheckOut.call();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    private boolean prepareStudentSubmission(String studentCode, String examCode, String pathServer,
                                             String pathDBOnline, String pathConnection, String pathScriptOnline,
                                             String pathOnlineTestFol,String studentFol, String extension) {
        boolean check = false;
        try {
            String studentSubmissionPath = PATH_SUBMISSIONS + File.separator
                    + examCode
                    + File.separator + studentCode + EXTENSION_ZIP;

            FileManager.unzip(studentSubmissionPath, pathServer);
            File pathDBFile = new File(pathDBOnline);
            File pathConnectionFile = new File(pathConnection);
            if (pathDBFile.exists()) {
                Files.copy(pathDBFile.toPath(), pathConnectionFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
//            File pathScriptOnlineSource = new File(pathScriptOnline);
//            File pathScriptTestOnlineFile = new File(pathOnlineTestFol + "Script" + extension);
//            if (pathScriptOnlineSource.exists() && pathScriptTestOnlineFile.exists()) {
//                Files.copy(pathScriptOnlineSource.toPath(), pathScriptTestOnlineFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            }
            // Prepare set copy student files to check duplicated code
            String sourceRepoPath = PATH_SUBMISSIONS + File.separator +
                    examCode + File.separator + "Sources" + File.separator + studentCode;
            File source = new File(sourceRepoPath);
            if (!source.exists()) {
                Files.createDirectories(source.toPath());
            }
            FileManager.copyAllFiles(studentFol, sourceRepoPath, extension);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    private void downloadTemplate(HttpServletResponse response, String practicalExamCode) {
        try {
            String filePath = PathConstants.PATH_PRACTICAL_EXAMS + File.separator + practicalExamCode + ".zip";
            File file = new File(filePath);
            OutputStream os = null;
            if (file.isFile()) {
                String mimeType = "application/octet-stream";
                response.setContentType(mimeType);
                response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
                response.setContentLength((int) file.length());
                os = response.getOutputStream();
                FileManager.downloadZip(file, os);

            } else {
                throw new CustomException(HttpStatus.CONFLICT, "Occur error ! Please try later");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("[THREAD-Checking]-");
        executor.initialize();
        return executor;
    }
}
