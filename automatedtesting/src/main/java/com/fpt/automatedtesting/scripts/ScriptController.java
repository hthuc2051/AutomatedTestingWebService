package com.fpt.automatedtesting.scripts;

import com.fpt.automatedtesting.common.ExcelFileDto;
import com.fpt.automatedtesting.common.ImportExcelFile;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamTemplateDto;
import com.fpt.automatedtesting.practicalexams.dtos.StudentSubmissionDto;
import com.fpt.automatedtesting.common.FileManager;
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
    @GetMapping("/scripts/subject/{subjectId}")
    public ResponseEntity<List<ScriptResponseDto>> getScriptBySubjectId(@PathVariable Integer subjectId) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.getScriptTestBySubjectId(subjectId));
    }

    @GetMapping("/scripts/{scriptId}")
    public ResponseEntity<ScriptResponseDto> getScriptByScriptId(@PathVariable Integer scriptId) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.getScriptTestByScriptId(scriptId));
    }

    @PostMapping("/scripts")
    public ResponseEntity<String> create(@ModelAttribute ScriptRequestDto scriptDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.generateScriptTest(scriptDto));
    }
    @PutMapping("/scripts")
    public ResponseEntity<String> update(@ModelAttribute ScriptRequestDto scriptDto) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.updateScriptTest(scriptDto));
    }
    @DeleteMapping("/scripts/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(scriptService.deleteScript(id));
    }

    @GetMapping("/testzip")
    public String getTestZip() throws IOException {

        return "ok";
    }

    @GetMapping("/scripts/download/{scriptId}")
    public void downloadTestScript(@PathVariable Integer scriptId, HttpServletResponse response) {
        scriptService.downloadScriptTest(scriptId,response);
    }

    @GetMapping("/documents/download/{scriptId}")
    public void downloadDocFile(@PathVariable Integer scriptId, HttpServletResponse response) {
        scriptService.downloadTestDocument(scriptId,response);
    }

    @GetMapping("/templateQuestion/download/{scriptId}")
    public void downloadTemplateQuestion(@PathVariable Integer scriptId, HttpServletResponse response) {
        scriptService.downloadTemplateQuestion(scriptId,response);
    }

    @GetMapping("/templateQuestionTemplate/download/{subjectId}")
    public void downloadTemplateQuestionTemplate(@PathVariable Integer subjectId, HttpServletResponse response) {
        scriptService.downloadTemplateQuestionTemplate(subjectId,response);
    }


    @GetMapping("/database/download/{scriptId}")
    public void downloadDatabase(@PathVariable Integer scriptId, HttpServletResponse response) {
        scriptService.downloadDatabaseScript(scriptId,response);
    }

    @GetMapping("/testData/download/{scriptId}")
    public void downloadTestData(@PathVariable Integer scriptId, HttpServletResponse response) {
        scriptService.downloadTestData(scriptId,response);
    }

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute StudentSubmissionDto file) throws IOException {
        FileManager.uploadFile(file);
        return "ok";
    }


    @PutMapping("/upload_excel")
    public String importExcelFile(@ModelAttribute ExcelFileDto excelDto) throws IOException {
        List<?> listReturn = ImportExcelFile.importExcelFileByRole(excelDto);
        System.out.println("aa");
        return "ok";
    }

    @GetMapping("/admin/test")
    public String testAction() throws IOException {

        return "";
    }

    @PostMapping("/upload_template")
    public String uploadTemplateExam(@ModelAttribute PracticalExamTemplateDto file) throws IOException {
        {
            FileManager.uploadTemplate(file);
        }
        return "ok";
    }
}
