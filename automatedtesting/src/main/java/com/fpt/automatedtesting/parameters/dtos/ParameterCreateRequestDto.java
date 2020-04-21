package com.fpt.automatedtesting.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterCreateRequestDto {
    private String name;
    private Boolean active;
}
