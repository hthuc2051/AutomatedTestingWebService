package com.fpt.automatedtesting.practicalexams;


import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamRequest;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamResponse;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamResultDto;
import com.fpt.automatedtesting.submissions.StudentSubmissionDetails;
import com.fpt.automatedtesting.submissions.Submission;
import com.fpt.automatedtesting.exception.CustomException;
import com.fpt.automatedtesting.submissions.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1998")
public class PracticalExamController {

    private final PracticalExamService practicalExamService;
    private final SubmissionRepository submissionRepository;
    private final PracticalExamRepository practicalExamRepository;

    @Autowired
    public PracticalExamController(PracticalExamService practicalExamService, SubmissionRepository submissionRepository, SubmissionRepository submissionRepository1, PracticalExamRepository practicalExamRepository) {
        this.practicalExamService = practicalExamService;
        this.submissionRepository = submissionRepository1;
        this.practicalExamRepository = practicalExamRepository;
    }

    @PostMapping("/practical-exam")
    public ResponseEntity<String> create(@RequestBody PracticalExamRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.create(dto));
    }

    @PutMapping("/practical-exam")
    public ResponseEntity<String> update(@RequestBody PracticalExamRequest dto) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(practicalExamService.create(dto));
        return null;
    }

    @DeleteMapping("/practical-exam/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
                return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.delete(id));
    }

    @PutMapping("/practical-exam/result")
    public ResponseEntity<Boolean> updateResult(@RequestBody PracticalExamResultDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.updatePracticalExamResult(dto));
    }

    @PostMapping("/practical-exam/lecturer/enroll")
    public ResponseEntity<List<PracticalExamResponse>> enrollPracticalExam(String enrollKey) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.getListPracticalExamByLecturer(enrollKey));
    }

    @GetMapping("/subjects/{id}/practical-exam")
    public ResponseEntity<List<PracticalExamResponse>> getPracticalExamsOfSubject(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.getPracticalExamsOfSubject(id));
    }

    @GetMapping("/lecturers/{id}/practical-exam")
    public ResponseEntity<List<PracticalExamResponse>> getPracticalExamsOfLecturer(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.getPracticalExamsOfLecturer(id));
    }




    @GetMapping("/templates/{id}")
    public void downLoadImportFile(@PathVariable Integer id, HttpServletResponse response) {
        practicalExamService.downloadPracticalTemplate(id, response);
    }

    @GetMapping("/practical-exam/{id}/students")
    public ResponseEntity<List<StudentSubmissionDetails>> getListStudentInPracticalExam(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.getListStudentInPracticalExam(id));
    }

    @GetMapping("/practical-exam/test/{id}")
    public ResponseEntity<List<StudentSubmissionDetails>> test(@PathVariable Integer id) {
        PracticalExam practicalExamEntity = practicalExamRepository
                .findByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Not found practical exam for Id: " + id));
        List<Submission> submissionList = submissionRepository.findAllByPracticalExamAndPracticalExam_ActiveAndActiveIsTrue(practicalExamEntity, true);
        return null;
    }
}