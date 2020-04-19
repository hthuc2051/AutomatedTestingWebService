package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionParamResponseDto {

    private Integer id;

    private ParamResponseDto param;

    private ParamTypeResponseDto paramType;

}
