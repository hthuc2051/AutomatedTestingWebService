package com.fpt.automatedtesting.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterTypeDto {
    Integer id;
    String name;
    String type;
}
