package com.fpt.automatedtesting.parametertypes;

import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeResponseDto;
import com.fpt.automatedtesting.parametertypes.dtos.ParameterTypeRequestDto;

import java.util.List;

public interface ParameterTypeService {
    List<ParameterTypeResponseDto> getParamTypeBySubjectId(Integer subjectId);

    List<ParameterTypeResponseDto> getAllParamType();

    String createParamType(ParameterTypeRequestDto dto);
    String deleteParamType(Integer id);

}
