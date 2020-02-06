package com.fpt.automatedtesting.controller;


import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.Script;
import com.fpt.automatedtesting.repository.ActionRepository;
import com.fpt.automatedtesting.repository.ScriptRepository;
import com.fpt.automatedtesting.service.PracticalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:1998")
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

    @PostMapping("/practicalexam")
    public void create(@RequestBody PracticalExamRequest dto){
        practicalExamService.create(dto);

    }
    @GetMapping("/import-file")
    public void downLoadImportFile(HttpServletResponse response){
        practicalExamService.downloadPracticalTemplate(response);

    }
}
