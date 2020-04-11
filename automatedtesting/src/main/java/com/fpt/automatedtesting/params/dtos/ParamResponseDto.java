package com.fpt.automatedtesting.params.dtos;

import com.fpt.automatedtesting.paramtypes.dtos.ParamTypeDetailsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamResponseDto {
    Integer id;
    String name;
    ParamTypeDetailsResponseDto type;
}
