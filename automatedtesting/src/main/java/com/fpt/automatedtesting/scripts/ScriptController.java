package com.fpt.automatedtesting.scripts;

import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.common.ExcelFileDto;
import com.fpt.automatedtesting.common.ImportExcelFile;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamTemplateDto;
import com.fpt.automatedtesting.practicalexams.dtos.UploadFileDto;
import com.fpt.automatedtesting.common.UploadFile;
import com.fpt.automatedtesting.scripts.dtos.ScriptRequestDto;
import com.fpt.automatedtesting.scripts.dtos.ScriptResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.*;

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
    @GetMapping("/scripts/{subjectId}")
    public ResponseEntity<List<ScriptResponseDto>> getScriptBySubjectId(@PathVariable Integer subjectId) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.getAll());
    }

    @PostMapping("/scripts")
    public ResponseEntity<Boolean> create(@ModelAttribute ScriptRequestDto scriptDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.generateScriptTest(scriptDto));
    }
    @PutMapping("/scripts")
    public ResponseEntity<Boolean> update(@ModelAttribute ScriptRequestDto scriptDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.updateScriptTest(scriptDto));
    }
    @DeleteMapping("/scripts/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.deleteScript(id));
    }

    @GetMapping("/testzip")
    public String getTestZip() throws IOException {

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


    @PutMapping("/upload_excel")
    public String importExcelFile(@ModelAttribute ExcelFileDto excelDto) throws IOException {
        List<?> listReturn = ImportExcelFile.importExcelFileByRole(excelDto);
        return "ok";
    }


    @PostMapping("/admin/action")
    public String postAdminAction(@RequestBody ActionRequestDto request) throws IOException {
        System.out.println(request.getName());
        return "ok";
    }

    @GetMapping("/admin/test")
    public String testAction() throws IOException {

        return "";
    }

    @PostMapping("/upload_template")
    public String uploadTemplateExam(@ModelAttribute PracticalExamTemplateDto file) throws IOException {
        {
            UploadFile.uploadTemplate(file);
        }
        return "ok";
    }


}
