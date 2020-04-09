package com.fpt.automatedtesting.paramtypes;

import java.util.List;

public interface ParamTypeService {
    List<ParamTypeResponseDto> getAllParamType();
    String insertParamType(ParamTypeRequestDto dto);
    String updateParamType(ParamTypeUpdateRequestDto dto);
    String deleteParamType(Integer id);
}
