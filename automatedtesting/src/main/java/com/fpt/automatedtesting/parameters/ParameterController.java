package com.fpt.automatedtesting.parameters;

import com.fpt.automatedtesting.parameters.dtos.ParameterCreateRequestDto;
import com.fpt.automatedtesting.parameters.dtos.ParameterResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1998")
public class ParameterController {

    private final ParameterService parameterService;

    @Autowired
    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping("/params")
    public ResponseEntity<List<ParameterResponseDto>> getAllParams() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parameterService.getAllParam());
    }

    @PostMapping("/params")
    public ResponseEntity<String> createParam(@RequestBody ParameterCreateRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parameterService.createParam(dto));
    }

    @DeleteMapping("/params/{id}")
    public ResponseEntity<String> deleteParam(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parameterService.deleteParam(id));
    }
}
