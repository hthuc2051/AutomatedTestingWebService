package com.fpt.automatedtesting.controller;


import com.fpt.automatedtesting.dto.request.EnrollDetailsDto;
import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.dto.request.PracticalExamResultDto;
import com.fpt.automatedtesting.dto.response.PracticalExamResponse;
import com.fpt.automatedtesting.dto.response.StudentSubmissionDetails;
import com.fpt.automatedtesting.service.PracticalExamService;
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

    @Autowired
    public PracticalExamController(PracticalExamService practicalExamService) {
        this.practicalExamService = practicalExamService;

    }

    @PostMapping("/practical-exam")
    public ResponseEntity<Boolean> create(@RequestBody PracticalExamRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(practicalExamService.create(dto));
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

}
