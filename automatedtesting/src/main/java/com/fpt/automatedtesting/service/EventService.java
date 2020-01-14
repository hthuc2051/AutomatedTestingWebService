package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;

import java.util.List;

public interface EventService {
    List<ActionResponseDto> getAllEvent();
    ActionResponseDto insertNewEvent(ActionRequestDto dto);
    ActionResponseDto updateEvent(ActionRequestDto dto);
    ActionResponseDto getEventById(int id);
    boolean deleteEventById(int id);
}
