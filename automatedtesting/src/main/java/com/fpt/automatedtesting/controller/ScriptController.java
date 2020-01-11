package com.fpt.automatedtesting.controller;

import com.fpt.automatedtesting.dto.request.TestScriptParamDto;
import com.fpt.automatedtesting.dto.response.ScriptResponseDto;
import com.fpt.automatedtesting.service.ScriptService;
import com.fpt.automatedtesting.utils.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScriptController {

    @Autowired
    private final ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }


    @GetMapping("/scripts")
    public ResponseEntity<List<ScriptResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.getAll());
    }

    @PostMapping("/testscript")
    @CrossOrigin(origins = "http://localhost:1998")
    public ResponseEntity<Boolean> generateTestScript(@RequestBody TestScriptParamDto scriptDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.generateScriptTest(scriptDto));
    }

    @GetMapping("/testzip")
    public String getTestZip() throws IOException {
        ZipFile.zipping(null);
        return "ok";
    }

    @GetMapping("/download")
    @CrossOrigin(origins = "http://localhost:1998")
    public ResponseEntity<Boolean> downloadFile(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.downloadFile(response));
    }

}
