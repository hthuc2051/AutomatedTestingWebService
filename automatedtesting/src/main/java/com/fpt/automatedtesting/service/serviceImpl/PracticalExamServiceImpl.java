package com.fpt.automatedtesting.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.automatedtesting.common.CustomConstant;
import com.fpt.automatedtesting.constants.PathConstants;
import com.fpt.automatedtesting.dto.PracticalInfo;
import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.dto.response.StudentSubmissionDetails;
import com.fpt.automatedtesting.entity.*;
import com.fpt.automatedtesting.entity.Class;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.*;
import com.fpt.automatedtesting.service.PracticalExamService;
import com.fpt.automatedtesting.utils.ZipFile;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PracticalExamServiceImpl implements PracticalExamService {

    private final PracticalExamRepository practicalExamRepository;
    private final ScriptRepository scriptRepository;
    private final SubmissionRepository submissionRepository;
    private final StudentClassRepository studentClassRepository;
    private final ClassRepository classRepository;

    @Autowired
    public PracticalExamServiceImpl(PracticalExamRepository practicalExamRepository, ScriptRepository scriptRepository, SubmissionRepository submissionRepository, StudentClassRepository studentClassRepository, ClassRepository classRepository) {
        this.practicalExamRepository = practicalExamRepository;
        this.scriptRepository = scriptRepository;
        this.submissionRepository = submissionRepository;
        this.studentClassRepository = studentClassRepository;
        this.classRepository = classRepository;
    }

    @Override
    public void create(PracticalExamRequest dto) {
        Class classRoom = classRepository
                .findById(dto.getClassId())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found class for id" + dto.getClassId()));
        List<StudentClass> studentClassList = studentClassRepository.findAllByClassRoom(classRoom);
        if (studentClassList != null && studentClassList.size() > 0) {
            List<Script> scriptEntities = null;
            List<Integer> listScriptId = dto.getListScripts();

            if (listScriptId != null && listScriptId.size() > 0) {
                scriptEntities = new ArrayList<>();
                for (Integer id : listScriptId) {
                    Script scriptEntity = scriptRepository.findById(id)
                            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found script " + id));
                    scriptEntities.add(scriptEntity);
                }
            }

            PracticalExam practicalExam = MapperManager.map(dto, PracticalExam.class);
            List<Submission> submissions = new ArrayList<>();

            for (StudentClass studentClass : studentClassList) {
                Submission submission = new Submission();
                submission.setActive(true);
                submission.setStudentClass(studentClass);
                submissions.add(submission);
                submission.setPracticalExam(practicalExam);
            }

            practicalExam.setScripts(scriptEntities);
            practicalExam.setSubmissions(submissions);

            practicalExamRepository.saveAndFlush(practicalExam);

        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, "No student from this class");
        }

    }

    @Override
    public void downloadPracticalTemplate(Integer templateId, HttpServletResponse response) {
        String practicalType = "";
        PracticalExam practicalExam = practicalExamRepository.
                findById(templateId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam"));
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
                throw new CustomException(HttpStatus.CONFLICT, "Occur error ! Try later");
            }

            // Copy script files
            File scriptFile = new File(practicalFol.getAbsolutePath() + File.separator + "TestScripts");
            check = scriptFile.mkdir();
            if (!check) {
                throw new CustomException(HttpStatus.CONFLICT, "Occur error ! Try later");
            }
            //copy source to target using Files Class
            try {
                // loop by list script test đã assign
                for (Script script : practicalExam.getScripts()) {
                    Path sourceScriptPath = Paths.get(PathConstants.PATH_SCRIPT_JAVA + script.getCode() + ".java");
                    Path targetScriptPath = Paths.get(scriptFile.getAbsolutePath() + File.separator + script.getCode() + ".java");
                    Files.copy(sourceScriptPath, targetScriptPath);
                    practicalType = script.getScriptType();
                }


                //copy server
                File sourceServerPath = new File(PathConstants.PATH_SERVER_JAVA_WEB);
                File targetServerPath = new File(practicalFol.getAbsolutePath() + File.separator + "Server");

                FileUtils.copyDirectory(sourceServerPath, targetServerPath);

                // Make info json files
                ObjectMapper objectMapper = new ObjectMapper();
                PracticalInfo practicalInfo = new PracticalInfo();
                practicalInfo.setName(practicalExam.getCode());
                practicalInfo.setType(practicalType);
                objectMapper.writeValue(
                        new FileOutputStream(practicalFol.getAbsoluteFile() +
                                File.separator
                                + CustomConstant.PRACTICAL_INFO_FILE_NAME),
                        practicalInfo);

            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomException(HttpStatus.CONFLICT, "Path incorrect");
            }

            try {
                // Zip folder
                ZipFile.zipFolder(practicalFol.getAbsolutePath(), practicalFol.getAbsolutePath());
                downloadTemplate(response, practicalExam.getCode());

            } catch (FileNotFoundException e) {
                throw new CustomException(HttpStatus.CONFLICT, e.getMessage());
            } catch (IOException e) {
                throw new CustomException(HttpStatus.CONFLICT, e.getMessage());
            }
        }
    }

    @Override
    public List<StudentSubmissionDetails> getListStudentInPracticalExam(Integer id) {
        List<StudentSubmissionDetails> result = null;
        PracticalExam practicalExamEntity = practicalExamRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam"));
        List<Submission> submissionList = submissionRepository.findAllByPracticalExam(practicalExamEntity);
        if (submissionList != null && submissionList.size() > 0) {
            result = new ArrayList<>();
            for (Submission submission : submissionList) {
                StudentSubmissionDetails dto = new StudentSubmissionDetails();
                StudentClass studentClass = submission.getStudentClass();
                if(studentClass== null)
                    throw new CustomException(HttpStatus.NOT_FOUND,"Not found Student class");

                Student student = studentClass.getStudent();
                if(student== null)
                    throw new CustomException(HttpStatus.NOT_FOUND,"Not found Student");
                dto.setId(submission.getId());
                dto.setStudentCode(student.getCode());
                dto.setStudentName(student.getName());
                dto.setScriptCode(submission.getScriptCode());

                result.add(dto);
            }
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
