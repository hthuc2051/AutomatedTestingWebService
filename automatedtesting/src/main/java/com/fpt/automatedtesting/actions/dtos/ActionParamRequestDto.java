package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.params.dtos.ParamRequestDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionParamRequestDto {
    private ParamRequestDto param;
    private ParamTypeRequestDto paramType;
}
