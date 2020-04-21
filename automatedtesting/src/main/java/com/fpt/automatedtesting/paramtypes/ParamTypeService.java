package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeDetailsResponseDto;
import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeRequestDto;

import java.util.List;

public interface ParamTypeService {
    List<ParamTypeDetailsResponseDto> getAllParamType();
    List<ParamTypeDetailsResponseDto> getParamTypeBySubjectId(Integer subjectId);
    String createParamType(ParamTypeRequestDto dto);
    String deleteParamType(Integer id);

}
