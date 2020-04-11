package com.fpt.automatedtesting.practicalexams;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fpt.automatedtesting.common.CustomConstant.*;
import static com.fpt.automatedtesting.common.PathConstants.*;

import com.fpt.automatedtesting.common.FileManager;
import com.fpt.automatedtesting.common.PathConstants;
import com.fpt.automatedtesting.duplicatedcode.DuplicatedCode;
import com.fpt.automatedtesting.duplicatedcode.DuplicatedCodeDetails;
import com.fpt.automatedtesting.duplicatedcode.DuplicatedCodeRepository;
import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeRequest;
import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeResponse;
import com.fpt.automatedtesting.practicalexams.dtos.*;
import com.fpt.automatedtesting.submissions.dtos.request.SubmissionDetailsDto;
import com.fpt.automatedtesting.submissions.StudentSubmissionDetails;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.lecturers.Lecturer;
import com.fpt.automatedtesting.lecturers.LecturerRepository;
import com.fpt.automatedtesting.common.MapperManager;
import com.fpt.automatedtesting.scripts.Script;
import com.fpt.automatedtesting.scripts.ScriptRepository;
import com.fpt.automatedtesting.students.Student;
import com.fpt.automatedtesting.subjects.Subject;
import com.fpt.automatedtesting.subjects.SubjectRepository;
import com.fpt.automatedtesting.subjectclasses.SubjectClass;
import com.fpt.automatedtesting.subjectclasses.SubjectClassRepository;
import com.fpt.automatedtesting.submissions.Submission;
import com.fpt.automatedtesting.submissions.SubmissionRepository;
import com.fpt.automatedtesting.users.UserRepository;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.*;


//TODO:Log file lại toàn bộ


@EnableAsync
@Service
public class PracticalExamServiceImpl implements PracticalExamService {
    private static final Logger logger = LogManager.getLogger(PracticalExamServiceImpl.class);

    //    Chứa vector của all SV
    private Map<String, Map<String, List<Double>>> allVectors = new HashMap<>();


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

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public PracticalExamServiceImpl(PracticalExamRepository practicalExamRepository, ScriptRepository scriptRepository, SubmissionRepository submissionRepository, UserRepository userRepository, SubjectClassRepository subjectClassRepository, DuplicatedCodeRepository duplicatedCodeRepository, LecturerRepository lecturerRepository, SubjectRepository subjectRepository, DuplicatedCodeService duplicatedCodeService) {
        this.practicalExamRepository = practicalExamRepository;
        this.scriptRepository = scriptRepository;
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.subjectClassRepository = subjectClassRepository;
        this.duplicatedCodeRepository = duplicatedCodeRepository;
        this.lecturerRepository = lecturerRepository;
        this.subjectRepository = subjectRepository;
        this.duplicatedCodeService = duplicatedCodeService;
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

                    PracticalExam practicalExam = MapperManager.map(dto, PracticalExam.class);
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

//            List<PracticalExam> result = practicalExamRepository.saveAll(saveEntities);
//            if (result == null) {
//                return "Create practical exam failed";
//            }
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
        String examCode = "";
        PracticalExam practicalExam = practicalExamRepository.
                findByIdAndActiveIsTrue(practicalExamId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam for Id:" + practicalExamId));

        // Create practical folder
        File practicalFol = new File(PathConstants.PATH_PRACTICAL_EXAMS + File.separator + practicalExam.getCode());
        boolean check = practicalFol.mkdir();
        if (!check) {
            downloadTemplate(response, practicalExam.getCode());
        } else {
            // Create submission folder
            File submissionFol = new File(practicalFol.getAbsolutePath() + File.separator + "Submissions");
            check = submissionFol.mkdir();
            if (!check) {
                throw new CustomException(HttpStatus.CONFLICT, "Occur error ! Please try later");
            }
            List<Student> students = practicalExam.getSubjectClass().getStudents();
            if (students == null) {
                throw new CustomException(HttpStatus.NOT_FOUND, "There are no student join this practical exam");
            }

            // write students details in practical exams to csv
            List<List<String>> rowsStudentsList = new ArrayList<>();
            List<List<String>> rowsStudentsResult = new ArrayList<>();

            rowsStudentsList.add(Arrays.asList(COLUMN_NO, COLUMN_STUDENT_CODE, COLUMN_STUDENT_NAME, COLUMN_SCRIPT_CODE,
                    COLUMN_SUBMITTED_TIME, COLUMN_EVALUATED_TIME, COLUMN_CODING_CONVENTION, COLUMN_RESULT,
                    COLUMN_TOTAL_POINT, COLUMN_ERROR));
            rowsStudentsResult.add(Arrays.asList(COLUMN_NO, COLUMN_STUDENT_CODE, COLUMN_STUDENT_NAME, COLUMN_SCRIPT_CODE,
                    COLUMN_TOTAL_POINT));

            List<Submission> submissionList = practicalExam.getSubmissions();
            for (int i = 0; i < submissionList.size(); i++) {
                Submission submission = submissionList.get(i);
                Student student = submission.getStudent();
                String fullName = student.getLastName();
                String middleName = student.getMiddleName();
                if (middleName != null && !middleName.equals("")) {
                    fullName += " " + student.getMiddleName();
                }
                fullName += " " + student.getFirstName();
                rowsStudentsList.add(Arrays.asList(String.valueOf(i + 1), student.getCode().trim(), fullName, submission.getScriptCode().trim()));
                rowsStudentsResult.add(Arrays.asList(String.valueOf(i + 1), student.getCode().trim(), fullName));
            }
            writeDataToCSVFile(practicalFol.getAbsolutePath() + File.separator + "Student_List.csv", rowsStudentsList);
            writeDataToCSVFile(practicalFol.getAbsolutePath() + File.separator + "Student_Results.csv", rowsStudentsResult);

            // Copy script files
            File scriptFol = new File(practicalFol.getAbsolutePath() + File.separator + "TestScripts");
            File docsFol = new File(practicalFol.getAbsolutePath() + File.separator + "ExamDocuments");
            boolean checkScriptFolCreated = scriptFol.mkdir();
            boolean checkDocFolCreate = docsFol.mkdir();
            if (!checkScriptFolCreated || !checkDocFolCreate) {
                throw new CustomException(HttpStatus.CONFLICT, "Occur error ! Please try later");
            }
            //copy source to target using Files Class
            try {
                // loop by list script test đã assign
                List<Script> scripts = practicalExam.getScripts();
                if (scripts != null) {
                    for (Script script : practicalExam.getScripts()) {
                        // For Test Scripts
                        Path sourceScriptPath = Paths.get(PathConstants.PATH_SCRIPT_JAVA + script.getCode() + ".java");
                        Path targetScriptPath = Paths.get(scriptFol.getAbsolutePath() + File.separator + script.getCode() + ".java");
                        Files.copy(sourceScriptPath, targetScriptPath);

                        // For docs
                        Path sourceDocPath = Paths.get(PathConstants.PATH_DOCS_JAVA + script.getCode() + ".docx");
                        Path targetDocPath = Paths.get(docsFol.getAbsolutePath() + File.separator + script.getCode() + ".docx");
                        Files.copy(sourceDocPath, targetDocPath);
                        examCode = PREFIX_EXAM_CODE + script.getSubject().getCode();
                    }

                    //copy server
                    File sourceServerPath = new File(PathConstants.PATH_SERVER_JAVA_WEB);
                    File targetServerPath = new File(practicalFol.getAbsolutePath() + File.separator + "Server");

                    FileUtils.copyDirectory(sourceServerPath, targetServerPath);

                    // Make info json files
                    ObjectMapper objectMapper = new ObjectMapper();
                    PracticalInfo practicalInfo = new PracticalInfo();
                    practicalInfo.setName(practicalExam.getCode());
                    practicalInfo.setExamCode(examCode);
                    objectMapper.writeValue(
                            new FileOutputStream(practicalFol.getAbsoluteFile() +
                                    File.separator
                                    + PRACTICAL_INFO_FILE_NAME),
                            practicalInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomException(HttpStatus.CONFLICT, "Path incorrect");
            }

            // Zip folder
            try {
                FileManager.zipFolder(practicalFol.getAbsolutePath(), practicalFol.getAbsolutePath());
                downloadTemplate(response, practicalExam.getCode());
            } catch (FileNotFoundException e) {
                throw new CustomException(HttpStatus.CONFLICT, "Cannot download practical exam ! Please try later");
            } catch (IOException e) {
                throw new CustomException(HttpStatus.CONFLICT, "Cannot download practical exam ! Please try later");
            }
        }
    }

    @Override
    public String delete(Integer id) {


//        for (int i = 0; i < 2; i++) {
//            duplicatedCodeService.getListTree("A.java", CODE_PRACTICAL_JAVA, "SE63155_A.java", vectorsOfStudent);
//            duplicatedCodeService.getListTree("B.java", CODE_PRACTICAL_JAVA, "SE63155_B.java", vectorsOfStudent);
//        }
//        allVectors.put(studentCode, vectorsOfStudent);
        System.out.println("AAA");
//        Double result = CosineSimilarity.computeSimilarity(summaryVector, summaryVectorB);


//        PracticalExam entity = practicalExamRepository
//                .findByIdAndActiveIsTrue(id)
//                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Practical exam is not found with Id " + id));
//        entity.setActive(false);
//        PracticalExam result = practicalExamRepository.save(entity);
//        if (result == null) {
//            throw new CustomException(HttpStatus.CONFLICT, "Delete practical exam failed ! Please try later");
//        }
        return "Delete practical exam successfully";
    }

    private void searchTheMostSimilarity(PracticalExam practicalExam) {
        List<DuplicatedResult> duplicatedResults = new ArrayList<>();
        Map<String, Double> checkedTokens = new HashMap<>();
        for (Map.Entry<String, Map<String, List<Double>>> entry : allVectors.entrySet()) {
            String studentCode = entry.getKey();
            DuplicatedResult duplicatedResult = new DuplicatedResult();
            duplicatedResult.setStudentCode(studentCode);
            Map<String, Map<String, Double>> comparedResult = new HashMap<>();
            logger.log(Level.INFO, "-----------------------------------------------------------------");
            logger.log(Level.INFO, "[CHECKING] - Student Code - " + studentCode);
            // Lấy danh sách các method vector của student 1
            Map<String, List<Double>> mapAllVectorOfStudent = entry.getValue();

            for (Map.Entry<String, List<Double>> entryVectorOfStudent : mapAllVectorOfStudent.entrySet()) {
                logger.log(Level.INFO, "[CHECKING] - Get submission file key " + entryVectorOfStudent.getKey());
                Map<String, Double> resultAfterComputeWithCosine = new HashMap<>();
                // Lấy vector thứ n đi so sánh
                List<Double> vectorsOfStudent = entryVectorOfStudent.getValue();
                computeCosineSimilarity(studentCode,
                        vectorsOfStudent,
                        resultAfterComputeWithCosine,
                        entryVectorOfStudent.getKey(),
                        checkedTokens);
                comparedResult.put(entryVectorOfStudent.getKey(), resultAfterComputeWithCosine);
            }
            duplicatedResult.setComparedResult(comparedResult);
//            duplicatedResult.setCheckedTokens(checkedTokens);
            duplicatedResults.add(duplicatedResult);

            // Make info json files
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                objectMapper.writeValue(
                        new FileOutputStream("a.json"),
                        checkedTokens);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.log(Level.INFO, "-----------------------------------------------------------------");
        }
        insertDuplicatedCode(checkedTokens, practicalExam);
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
                System.out.println(arr.length);
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
                System.out.println("So sánh : " + entry.getKey());
                if (checkedTokens != null && checkedTokens.size() > 0) {
                    String firstStudent = arr[0];
                    String secondStudent = arr[1];
                    List<DuplicatedCodeDetails> list = new ArrayList<>();
                    for (Map.Entry<String, Double> entryToken : checkedTokens.entrySet()) {
                        String filesToken = entryToken.getKey();
                        if (filesToken.contains(firstStudent) && filesToken.contains(secondStudent)) {
                            DuplicatedCodeDetails details = new DuplicatedCodeDetails();
                            details.setDuplicatedCode(responseEntity);
                            details.setFilesToken(filesToken);
                            list.add(details);
                        }
                    }
                    responseEntity.setDuplicatedCodeDetails(list);
                    DuplicatedCode check = duplicatedCodeRepository.saveAndFlush(responseEntity);
                    if (check != null) {
                        System.out.println("ok");
                    }
                }
            }
        }
        System.out.println(result);
    }

    private void computeCosineSimilarity(String studentCode, List<Double> inputVector, Map<String, Double> resultAfterComputeWithCosine, String inputToken, Map<String, Double> checkedTokens) {
        for (Map.Entry<String, Map<String, List<Double>>> entry : allVectors.entrySet()) {
            if (!studentCode.equalsIgnoreCase(entry.getKey())) {
                logger.log(Level.INFO, "[CHECKING] - Checking with " + entry.getKey());
                // Lấy danh sách vector của student cần compare;
                Map<String, List<Double>> mapAllVectorOfOtherStudent = entry.getValue();
                for (Map.Entry<String, List<Double>> entryVectorOfOtherStudent : mapAllVectorOfOtherStudent.entrySet()) {
                    String key = entryVectorOfOtherStudent.getKey();
                    String pairToken = inputToken + "~" + key;
                    String pairTokenSwap = key + "~" + inputToken;
                    boolean check = !checkedTokens.containsKey(pairToken) && !checkedTokens.containsKey(pairTokenSwap);
                    if (check) {
                        List<Double> studentVector = entryVectorOfOtherStudent.getValue();
                        double computeResult = CosineSimilarity.computeSimilarity(inputVector, studentVector);
                        logger.log(Level.INFO, "[CHECKING] - Checking with submission file key "
                                + key + " | " + computeResult * 100 + "%");
                        if (computeResult > 0.5) {
                            resultAfterComputeWithCosine.put(key, computeResult);
                            checkedTokens.put(pairToken, (double) Math.round(computeResult * 100) / 100);
                        }
                    } else {
                        logger.log(Level.INFO, "[CHECKING] - Meet checked token :" + pairToken);
                    }
                }
            }
        }
        logger.log(Level.INFO, "-----------------------------------------");
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
    public List<PracticalExamResponse> getListPracticalExamByLecturer(String code) {
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
                    result = MapperManager.mapAll(practicalExams, PracticalExamResponse.class);
                    if (result != null) {
                        for (PracticalExamResponse practicalExamDto : result) {
                            practicalExamDto.setSubjectCode(subjectClass.getSubject().getCode());
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
                if (practicalExams != null && practicalExams.size() > 0) {
                    result = MapperManager.mapAll(practicalExams, PracticalExamResponse.class);
                    if (result != null) {
                        for (PracticalExamResponse practicalExamDto : result) {
                            practicalExamDto.setClassCode(subjectClass.getAClass().getClassCode());
                            practicalExamDto.setSubjectCode(subjectClass.getSubject().getCode());
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
    public String getStudentSubmission(StudentSubmissionDto dto) {

        MultipartFile file = dto.getFile();
        String copyLocation = PATH_SUBMISSIONS + File.separator +
                dto.getExamCode() + File.separator;
        Path pathLocation = Paths.get(copyLocation
                + StringUtils.cleanPath(file.getOriginalFilename()));
        try {
            Files.copy(file.getInputStream(), pathLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File folder = new File(copyLocation + "Sources" + File.separator + dto.getStudentCode());
        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (folder.exists()) {
            String s = pathLocation.toAbsolutePath().toString();
            FileManager.unzip(s, folder.getAbsolutePath());
        }

        return "Successfully";
    }


    @Async
    @EventListener
    public void processChecking(PracticalInfo info) {
        PracticalExam practicalExam = practicalExamRepository.findByCodeAndActiveIsTrue(info.getExamCode())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found id for Id:" + info.getExamCode()));
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
        } else if (info.getExamCode().contains(CODE_PRACTICAL_CSharp)) {
            extension = ".cs";
        } else if (info.getExamCode().contains(CODE_PRACTICAL_C)) {
            extension = ".c";
        }

        for (String studentCode : allStudentSubmissionFileName) {
            List<File> studentFiles = new ArrayList<>();
            FileManager.getAllFiles(sourcePath + File.separator + studentCode, studentFiles, extension);
            Map<String, List<Double>> vectors = new HashMap<>();
            if (!studentFiles.isEmpty()) {
                for (File studentFile : studentFiles) {
                    if (!studentFile.getName().contains("TemplateQuestion")) {
                        //TODO: For extend later
//                    File file = checkValidFile(studentFile);
                        String filePath = studentFile.getAbsolutePath();
                        try {
                            Files.copy(Paths.get(filePath),
                                    Paths.get(PATH_SERVER_REPOSITORY + File.separator + info.getExamCode() + "_" + studentCode + "_" + studentFile.getName()),
                                    StandardCopyOption.REPLACE_EXISTING
                            );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        PracticalExamUtils.checkDuplicatedCodeGithub(filePath);
                        duplicatedCodeService.getListTree(filePath, CODE_PRACTICAL_JAVA, studentCode + "_" + studentFile.getName(), vectors);
                    }
                }
            }
            allVectors.put(studentCode, vectors);
        }
        searchTheMostSimilarity(practicalExam);
    }

    private File checkValidFile(File studentFile) {
        File result = null;
        try {
            String s = FileManager.readFileToString(studentFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public String checkDuplicatedCode(PracticalInfo info) {
        applicationEventPublisher.publishEvent(info);
        return "Start checking duplicated code successfully !";
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
                    for (DuplicatedCodeDetails details: duplicatedCodeDetails) {
                        filesTokens.add(details.getFilesToken());
                    }
                    dto.setDuplicatedCodeDetails(filesTokens);
                }
                result.add(dto);
            }
        }
        return result;
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
