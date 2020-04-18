package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;

import java.util.List;

public interface ParamTypeService {
    List<ParamTypeResponseDto> getAllParamType();
    String createParamType(ParamTypeRequestDto dto);
    String deleteParamType(Integer id);

}
