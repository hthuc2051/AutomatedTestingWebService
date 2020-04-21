package com.fpt.automatedtesting.parametertypes;

import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeResponseDto;
import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1998")
public class ParameterTypeController {

    private final ParameterTypeService parameterTypeService;

    @Autowired
    public ParameterTypeController(ParameterTypeService parameterTypeService) {
        this.parameterTypeService = parameterTypeService;
    }

    @GetMapping("/param-type")
    public ResponseEntity<List<ParameterTypeResponseDto>> getAllParamTypes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parameterTypeService.getAllParamType());
    }

    @GetMapping("/param-type/{subjectId}")
    public ResponseEntity<List<ParameterTypeResponseDto>> getParamTypeBySubjectId(@PathVariable Integer subjectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parameterTypeService.getParamTypeBySubjectId(subjectId));
    }

    @PostMapping("/param-type")
    public ResponseEntity<String> insertNewParamType(@RequestBody ParameterTypeRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parameterTypeService.createParamType(requestDto));
    }

    @DeleteMapping("/param-type/{id}")
    public ResponseEntity<String> deleteParamType(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(parameterTypeService.deleteParamType(id));
    }

}
