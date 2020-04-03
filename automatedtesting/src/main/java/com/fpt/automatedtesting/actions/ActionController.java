package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseSubjectIdDto;
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

    // Return data to lecturer
    @GetMapping("/actions")
    public ResponseEntity<List<ActionResponseDto>> getAllActions() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.getAll());
    }

    @GetMapping("/actions/subjects/{subjectId}")
    public ResponseEntity<List<ActionResponseSubjectIdDto>> getAllActionsBySubject(@PathVariable Integer subjectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.getAllActionBySubject(subjectId));
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
