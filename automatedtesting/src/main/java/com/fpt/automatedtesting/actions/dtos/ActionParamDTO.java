package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import com.fpt.automatedtesting.params.dtos.ParamTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionParamDTO {
    private Integer id;
    private String name;
    private String code;
    private List<ParamTypeDTO> params =  new ArrayList<>();
    private List<Integer> subjectId =  new ArrayList<>();
}
