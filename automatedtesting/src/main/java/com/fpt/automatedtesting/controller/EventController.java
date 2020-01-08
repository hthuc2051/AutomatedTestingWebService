package com.fpt.automatedtesting.controller;

import com.fpt.automatedtesting.dto.response.EventResponseDto;
import com.fpt.automatedtesting.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController  {

    @Autowired
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Get data for manager
    @GetMapping("/events")
    public ResponseEntity<List<EventResponseDto>> getAllEvent() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(eventService.getAllEvent());
    }


    // return data lecturer
}
