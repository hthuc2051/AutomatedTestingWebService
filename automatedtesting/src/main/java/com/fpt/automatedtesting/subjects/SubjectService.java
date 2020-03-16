package com.fpt.automatedtesting.subjects;

import com.fpt.automatedtesting.classes.ClassResponseDto;
import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;

import java.util.List;

public interface SubjectService {
    List<SubjectResponseDto> getAll();
    List<ClassResponseDto> getAllClassBySubjectIncludeSubjectClassId(Integer subjectId);
}
