package com.fpt.automatedtesting.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fpt.automatedtesting.common.CustomConstant.*;

import com.fpt.automatedtesting.constants.PathConstants;
import com.fpt.automatedtesting.dto.PracticalInfo;
import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.dto.request.PracticalExamResultDto;
import com.fpt.automatedtesting.dto.request.SubmissionDetailsDto;
import com.fpt.automatedtesting.dto.response.PracticalExamResponse;
import com.fpt.automatedtesting.dto.response.StudentSubmissionDetails;
import com.fpt.automatedtesting.entity.*;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.*;
import com.fpt.automatedtesting.service.PracticalExamService;
import com.fpt.automatedtesting.utils.ZipFile;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


//TODO:Log file lại toàn bộ

@Service
public class PracticalExamServiceImpl implements PracticalExamService {

    private static final String PREFIX_EXAM_CODE = "Practical_";

    private final PracticalExamRepository practicalExamRepository;
    private final ScriptRepository scriptRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final SubjectClassRepository subjectClassRepository;
    private final LecturerRepository lecturerRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public PracticalExamServiceImpl(PracticalExamRepository practicalExamRepository, ScriptRepository scriptRepository, SubmissionRepository submissionRepository, UserRepository userRepository, SubjectClassRepository subjectClassRepository, LecturerRepository lecturerRepository, SubjectRepository subjectRepository) {
        this.practicalExamRepository = practicalExamRepository;
        this.scriptRepository = scriptRepository;
        this.submissionRepository = submissionRepository;
        this.userRepository = userRepository;
        this.subjectClassRepository = subjectClassRepository;
        this.lecturerRepository = lecturerRepository;
        this.subjectRepository = subjectRepository;
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
    public Boolean updatePracticalExamResult(PracticalExamResultDto practicalExamResultDto) {
        PracticalExam practicalExam = practicalExamRepository
                .findByIdAndActiveIsTrue(practicalExamResultDto.getId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found class for id" + practicalExamResultDto.getId()));
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
                rowsStudentsList.add(Arrays.asList(String.valueOf(i + 1), student.getCode().trim(), student.getName().trim(), submission.getScriptCode().trim()));
                rowsStudentsResult.add(Arrays.asList(String.valueOf(i + 1), student.getCode().trim(), student.getName().trim()));
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
                ZipFile.zipFolder(practicalFol.getAbsolutePath(), practicalFol.getAbsolutePath());
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
                dto.setStudentName(student.getName());
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
            for (SubjectClass subjectClass : subjectClassList) {
                List<PracticalExam> practicalExams = subjectClass.getPracticalExams();
                if (practicalExams != null && practicalExams.size() > 0) {
                    result = MapperManager.mapAll(practicalExams, PracticalExamResponse.class);
                    if (result != null) {
                        for (PracticalExamResponse practicalExamDto : result) {
                            practicalExamDto.setSubjectCode(subject.getCode());
                            practicalExamDto.setSubjectId(subject.getId());
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
    public List<PracticalExamResponse> getListPracticalExamByLecturer(String enrollKey) {
        Lecturer lecturer = lecturerRepository.findByEnrollKeyAndActiveIsTrue(enrollKey);
        if (lecturer == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Not found lecturer for key enroll " + enrollKey);
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


    private void downloadTemplate(HttpServletResponse response, String practicalExamCode) {
        // Download
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
                ZipFile.downloadZip(file, os);

            } else {
                throw new CustomException(HttpStatus.CONFLICT, "Occur error ! Please try later");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
