package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.params.dtos.ParamTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionParamDto {
    private Integer id;
    private String name;
    private String code;
    private List<ParamTypeDto> params =  new ArrayList<>();
    private String subjectCode;
}
