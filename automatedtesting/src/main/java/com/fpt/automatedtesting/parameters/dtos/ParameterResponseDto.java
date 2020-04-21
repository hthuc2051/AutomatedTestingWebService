package com.fpt.automatedtesting.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterResponseDto {
    Integer id;
    String name;
    Boolean active;
}
