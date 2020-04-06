package com.fpt.automatedtesting.paramtypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1998")
public class ParamTypeController {

    private final ParamTypeService paramTypeService;

    @Autowired
    public ParamTypeController(ParamTypeService paramTypeService) {
        this.paramTypeService = paramTypeService;
    }

    @GetMapping("/param-type")
    public ResponseEntity<List<ParamTypeResponseDto>> getAllParamTypes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paramTypeService.getAllParamType());
    }

}
