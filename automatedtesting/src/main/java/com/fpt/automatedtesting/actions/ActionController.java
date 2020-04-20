package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionParamDto;
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

    @GetMapping("/action")
    public ResponseEntity<List<ActionResponseDto>> getAllActions() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.getAllActions());
    }

    @GetMapping("/action/subject/{subjectId}")
    public ResponseEntity<List<ActionParamDto>> getAllActionsBySubject(@PathVariable Integer subjectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.getAllActionBySubject(subjectId));
    }

    @PostMapping("/action")
    public ResponseEntity<String> insertNewActions(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.createAction(dto));
    }

    @DeleteMapping("/action/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.deleteAction(id));
    }

    @PutMapping("/action")
    public ResponseEntity<String> updateAction(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.updateAction(dto));
    }
}
