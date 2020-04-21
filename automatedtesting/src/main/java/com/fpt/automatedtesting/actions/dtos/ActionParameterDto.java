package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.parameters.dtos.ParameterTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionParameterDto {
    private Integer id;
    private String name;
    private String code;
    private List<ParameterTypeDto> params =  new ArrayList<>();
    private String subjectCode;
}
