package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.EventRequestDto;
import com.fpt.automatedtesting.dto.response.EventResponseDto;

import java.util.List;

public interface EventService {
    List<EventResponseDto> getAllEvent();
    EventResponseDto insertNewEvent(EventRequestDto dto);
}
