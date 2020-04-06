package com.fpt.automatedtesting.paramtypes;

import java.util.List;

public interface ParamTypeService {
    List<ParamTypeResponseDto> getAllParamType();
    String insert(ParamTypeRequestDto dto);
}
