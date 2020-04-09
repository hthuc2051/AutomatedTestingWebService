package com.fpt.automatedtesting.params;

import com.fpt.automatedtesting.params.dtos.ParamCreateRequestDto;
import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import com.fpt.automatedtesting.params.dtos.ParamUpdateRequestDto;

import java.util.List;

public interface ParamService {
    List<ParamResponseDto> getAllParam();
    String createParam(ParamCreateRequestDto dto);
//    String updateParam(ParamUpdateRequestDto dto);
//    String deleteParam(Integer id);
}
