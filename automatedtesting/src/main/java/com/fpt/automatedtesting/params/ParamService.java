package com.fpt.automatedtesting.params;

import com.fpt.automatedtesting.params.dtos.ParamCreateRequestDto;
import com.fpt.automatedtesting.params.dtos.ParamResponseDto;

import java.util.List;

public interface ParamService {
    List<ParamResponseDto> getAllParam();
    String createParam(ParamCreateRequestDto dto);
    String deleteParam(Integer id);
}
