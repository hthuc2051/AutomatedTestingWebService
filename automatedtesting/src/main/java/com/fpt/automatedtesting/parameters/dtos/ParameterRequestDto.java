package com.fpt.automatedtesting.parameters.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterRequestDto {
    private Integer id;
    private String name;
    private Boolean active;
}
