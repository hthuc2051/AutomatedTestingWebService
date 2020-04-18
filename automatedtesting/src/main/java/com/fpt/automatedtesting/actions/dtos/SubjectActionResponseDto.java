package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.subjects.dtos.SubjectResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectActionResponseDto {

    private Integer id;

    private SubjectResponseDto subject;

    private List<SubjectActionParamResponseDto> subjectActionParams;
}
