package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.response.ClassResponseDto;
import com.fpt.automatedtesting.dto.response.SubjectResponseDto;

import java.util.List;

public interface SubjectService {
    List<SubjectResponseDto> getAll();
    List<ClassResponseDto> getAllClassBySubjectIncludeSubjectClassId(Integer subjectId);
}
