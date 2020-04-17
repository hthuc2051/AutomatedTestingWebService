package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeDetailsResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeUpdateRequestDto;

import java.util.List;

public interface ParamTypeService {
    List<ParamTypeDetailsResponseDto> getAllParamType();
    String createParamType(ParamTypeRequestDto dto);
    String updateParamType(ParamTypeUpdateRequestDto dto);
    String deleteParamType(Integer id);

}
