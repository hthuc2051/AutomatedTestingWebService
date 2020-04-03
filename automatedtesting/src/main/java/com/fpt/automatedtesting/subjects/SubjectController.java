package com.fpt.automatedtesting.subjects;

import com.fpt.automatedtesting.classes.ClassResponseDto;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:1998")
@RequestMapping("/api")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getAllSubject() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAll());
    }

    @GetMapping("/subjects/{id}/classes-scripts")
    public ResponseEntity<SubjectResponseDto> getAllClassAndScriptsBySubjectId(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subjectService.getAllClassAndScriptsBySubjectId(id));
    }

}
