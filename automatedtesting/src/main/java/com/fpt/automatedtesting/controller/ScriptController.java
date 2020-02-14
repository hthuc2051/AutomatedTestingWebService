package com.fpt.automatedtesting.controller;

import com.fpt.automatedtesting.dto.response.ScriptResponseDto;
import com.fpt.automatedtesting.service.ScriptService;
import com.fpt.automatedtesting.dto.request.*;
import com.fpt.automatedtesting.utils.UploadFile;
import com.fpt.automatedtesting.utils.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1998")
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

    @PostMapping("/scripts")
    public ResponseEntity<Boolean> create(@RequestBody ScriptRequestDto scriptDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.generateScriptTest(scriptDto));
    }


    @GetMapping("/testzip")
    public String getTestZip() throws IOException {
        ZipFile.zipFolder(null, null);
        return "ok";
    }

    @GetMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        scriptService.downloadFile(response);
    }

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute UploadFileDto file) throws IOException {
        UploadFile.uploadFile(file);
        return "ok";
    }


    @PostMapping("/admin/action")
    public String postAdminAction(@RequestBody ActionRequestDto request) throws IOException {
        System.out.println(request.getName());
        return "ok";
    }

    @GetMapping("/admin/test")
    public String testAction() throws IOException {
        ZipFile.deleteFolder("G:\\New folder (5)\\deleted");
        return "ok";
    }

}
