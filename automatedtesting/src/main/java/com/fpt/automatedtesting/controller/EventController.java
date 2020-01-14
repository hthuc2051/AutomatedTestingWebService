package com.fpt.automatedtesting.controller;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;
import com.fpt.automatedtesting.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Return data to lecturer
    @GetMapping("/events")
    @CrossOrigin(origins ="http://localhost:1998")
    public ResponseEntity<List<ActionResponseDto>> getAllEvent() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.getAllEvent());
    }

    // Get data for manager
    @CrossOrigin(origins ="http://localhost:1998")
    @PostMapping("/events")
    public ResponseEntity<ActionResponseDto> insertNewEvent(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.insertNewEvent(dto));
    }
    @CrossOrigin(origins ="http://localhost:1998")
    @GetMapping("/event")
    public ResponseEntity<ActionResponseDto> getEvent(@RequestParam int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.getEventById(id));
    }
    @CrossOrigin(origins ="http://localhost:1998")
    @DeleteMapping("/event/delete")
    public ResponseEntity<Boolean> deleteEvent(@RequestParam int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.deleteEventById(id));
    }
    @CrossOrigin(origins ="http://localhost:1998")
    @PutMapping("/event/put")
    public ResponseEntity<ActionResponseDto> updateEvent(@RequestBody ActionRequestDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.updateEvent(dto));
    }
}
