package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionParameterDto;
import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins ="http://localhost:1998")
public class ActionController {

    private final ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/actions/all/subject/{subjectId}")
    public ResponseEntity<List<ActionResponseDto>> getAllActionsBySubjectId(@PathVariable Integer subjectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.getAllActionsBySubjectId(subjectId));
    }

    @GetMapping("/actions/subject/{subjectId}")
    public ResponseEntity<List<ActionParameterDto>> getAllActionsBySubject(@PathVariable Integer subjectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.getAllActionBySubject(subjectId));
    }

    @PostMapping("/actions")
    public ResponseEntity<String> insertNewActions(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.createAction(dto));
    }

    @DeleteMapping("/actions/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.deleteAction(id));
    }

    @PutMapping("/actions")
    public ResponseEntity<String> updateAction(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.updateAction(dto));
    }
}
