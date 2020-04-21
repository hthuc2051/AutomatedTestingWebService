package com.fpt.automatedtesting.parameters;

import com.fpt.automatedtesting.parameters.dtos.ParameterCreateRequestDto;
import com.fpt.automatedtesting.parameters.dtos.ParameterResponseDto;

import java.util.List;

public interface ParameterService {
    List<ParameterResponseDto> getAllParam();
    String createParam(ParameterCreateRequestDto dto);
    String deleteParam(Integer id);
}
