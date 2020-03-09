package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.ActionRequestDto;
import com.fpt.automatedtesting.dto.response.ActionResponseDto;

import java.util.List;

public interface ActionService {
    List<ActionResponseDto> getAll();
    ActionResponseDto insert(ActionRequestDto dto);
    ActionResponseDto update(ActionRequestDto dto);
    ActionResponseDto findById(int id);
    String delete(int id);
}
