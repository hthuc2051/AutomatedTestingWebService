package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionParamDto;
import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;

import java.util.List;

public interface ActionService {
    List<ActionResponseDto> getAll();
    String insertAction(ActionRequestDto dto);
    String update(ActionRequestDto dto);
    ActionResponseDto findById(int id);
    List<ActionParamDto> getAllActionBySubject(int subjectId);
    String delete(int id);
}
