package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionParamDto;
import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;

import java.util.List;

public interface ActionService {
    List<ActionResponseDto> getAllActions();
    String createAction(ActionRequestDto dto);
    String updateAction(ActionRequestDto dto);
    List<ActionParamDto> getAllActionBySubject(int subjectId);
    String deleteAction(int id);
}
