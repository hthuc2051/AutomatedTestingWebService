package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.actions.dtos.ActionRequestDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseDto;
import com.fpt.automatedtesting.actions.dtos.ActionResponseSubjectIdDto;

import java.util.List;

public interface ActionService {
    List<ActionResponseDto> getAll();
    ActionResponseDto insert(ActionRequestDto dto);
    ActionResponseDto update(ActionRequestDto dto);
    ActionResponseDto findById(int id);
    List<ActionResponseSubjectIdDto> getAllActionBySubject(int subjectId);
    String delete(int id);
}
