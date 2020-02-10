package com.fpt.automatedtesting.controller;


import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.dto.response.StudentSubmissionDetails;
import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.Script;
import com.fpt.automatedtesting.repository.ActionRepository;
import com.fpt.automatedtesting.repository.ScriptRepository;
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
    private final ScriptRepository scriptRepository;
    private final ActionRepository actionRepository;

    @Autowired
    public PracticalExamController(PracticalExamService practicalExamService, ScriptRepository scriptRepository, ActionRepository actionRepository) {
        this.practicalExamService = practicalExamService;
        this.scriptRepository = scriptRepository;
        this.actionRepository = actionRepository;
    }

    @PostMapping("/practical-exam")
    public void create(@RequestBody PracticalExamRequest dto) {
        practicalExamService.create(dto);

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
