package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.parameters.dtos.ParameterResponseDto;
import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionParameterResponseDto {

    private Integer id;

    private ParameterResponseDto param;

    private ParameterTypeResponseDto paramType;

}
