package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeUpdateRequestDto;

import java.util.List;

public interface ParamTypeService {
    List<ParamTypeResponseDto> getAllParamType();
    String insertParamType(ParamTypeRequestDto dto);
    String updateParamType(ParamTypeUpdateRequestDto dto);
    String deleteParamType(Integer id);

}
