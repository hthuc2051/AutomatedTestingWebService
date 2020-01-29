package com.fpt.automatedtesting.service.serviceImpl;

import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.entity.*;
import com.fpt.automatedtesting.entity.Class;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.mapper.MapperManager;
import com.fpt.automatedtesting.repository.*;
import com.fpt.automatedtesting.service.PracticalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
}
