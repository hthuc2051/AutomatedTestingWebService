package com.fpt.automatedtesting.subjects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalInfo;
import com.fpt.automatedtesting.subjects.dtos.SubjectDetailsResponseDto;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.errors.UnmergedPathException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.fpt.automatedtesting.common.CustomConstant.PRACTICAL_INFO_FILE_NAME;

@RestController
@CrossOrigin(origins = "http://localhost:1998")
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/test")
    public String test() {
        File file = new File("A/faf/fa/EXAMS_");
        String s = file.getParent();
        System.out.println(s);
        return "";
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectDetailsResponseDto>> getAllSubject() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAll());
    }

    @GetMapping("/subjects/{id}/classes-scripts")
    public ResponseEntity<SubjectDetailsResponseDto> getAllClassAndScriptsBySubjectId(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAllClassAndScriptsBySubjectId(id));
    }

    @GetMapping("/subjects/all")
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjectForParamType() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAllSubjectForParamType());
    }

}
