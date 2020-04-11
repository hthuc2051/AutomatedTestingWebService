package com.fpt.automatedtesting.subjects;

import com.fpt.automatedtesting.subjects.dtos.SubjectDetailsResponseDto;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDetailsResponseDto> getAll();
    SubjectDetailsResponseDto getAllClassAndScriptsBySubjectId(Integer subjectId);
    List<SubjectResponseDto> getAllSubjectForParamType();
}
