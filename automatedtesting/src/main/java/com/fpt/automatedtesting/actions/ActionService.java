package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionParameterDto;
import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;

import java.util.List;

public interface ActionService {
    List<ActionResponseDto> getAllActionsBySubjectId(int subjectId);
    String createAction(ActionRequestDto dto);
    String updateAction(ActionRequestDto dto);
    List<ActionParameterDto> getAllActionBySubject(int subjectId);
    String deleteAction(int id);
}
