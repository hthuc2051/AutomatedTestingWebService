package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionParamDTO;
import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseSubjectIdDto;

import java.util.List;

public interface ActionService {
    List<ActionResponseDto> getAll();
    ActionResponseDto insertAction(ActionRequestDto dto);
    ActionResponseDto update(ActionRequestDto dto);
    ActionResponseDto findById(int id);
    List<ActionParamDTO> getAllActionBySubject(int subjectId);
    String delete(int id);
}
