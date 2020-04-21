package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.parameters.dtos.ParameterRequestDto;
import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionParameterRequestDto {
    private ParameterRequestDto param;
    private ParameterTypeDetailsDto paramType;
}
