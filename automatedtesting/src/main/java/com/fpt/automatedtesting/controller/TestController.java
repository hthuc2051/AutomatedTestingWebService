package com.fpt.automatedtesting.controller;

import com.fpt.automatedtesting.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> getString(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(testService.dummyString());
    }
    
}
