package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.params.ParamResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionResponseSubjectIdDto {
    private Integer id;
    private String name;
    private String code;
    private List<ParamResponseDto> params;
    private List<Integer> subjectId;
}
