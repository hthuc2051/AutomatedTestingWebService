package com.fpt.automatedtesting.submissions;

import com.fpt.automatedtesting.submissions.dtos.request.SubmissionFilesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1998")
public class SubmissionController {

    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submission/file")
    public ResponseEntity<Map<String,List<String>>> getFile(@RequestBody SubmissionFilesRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(submissionService.getSubmissionFiles(request));
    }
}
