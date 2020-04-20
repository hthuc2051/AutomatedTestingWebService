package com.fpt.automatedtesting.params;

import com.fpt.automatedtesting.params.dtos.ParamCreateRequestDto;
import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:1998")
public class ParamController {

    private final ParamService paramService;

    @Autowired
    public ParamController(ParamService paramService) {
        this.paramService = paramService;
    }

    @GetMapping("/param")
    public ResponseEntity<List<ParamResponseDto>> getAllParams() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paramService.getAllParam());
    }

    @PostMapping("/param")
    public ResponseEntity<String> createParam(@RequestBody ParamCreateRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paramService.createParam(dto));
    }

    @DeleteMapping("/param/{id}")
    public ResponseEntity<String> deleteParam(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paramService.deleteParam(id));
    }
}
