package com.fpt.automatedtesting.controller;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;
import com.fpt.automatedtesting.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:1998")
@RequestMapping("/api")
public class ActionController {

    @Autowired
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    // Return data to lecturer
    @GetMapping("/actions")
    public ResponseEntity<List<ActionResponseDto>> getAllActions() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.getAll());
    }

    @PostMapping("/actions")
    public ResponseEntity<ActionResponseDto> insertNewActions(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.insert(dto));
    }

    @GetMapping("/actions/{id}")
    public ResponseEntity<ActionResponseDto> getAction(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.findById(id));
    }

    @DeleteMapping("/actions/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.delete(id));
    }

    @PutMapping("/action")
    public ResponseEntity<ActionResponseDto> updateAction(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.update(dto));
    }
}
