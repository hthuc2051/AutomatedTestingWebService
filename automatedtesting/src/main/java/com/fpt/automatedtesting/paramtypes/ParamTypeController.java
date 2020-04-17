package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeDetailsResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ParamTypeDetailsResponseDto>> getAllParamTypes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paramTypeService.getAllParamType());
    }

    @PostMapping("/param-type")
    public ResponseEntity<String> insertNewParamType(@RequestBody ParamTypeRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paramTypeService.createParamType(requestDto));
    }

    @DeleteMapping("/param-type/{id}")
    public ResponseEntity<String> deleteParamType(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paramTypeService.deleteParamType(id));
    }

}
