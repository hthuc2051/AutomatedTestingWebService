package com.fpt.automatedtesting.parametertypes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterTypeResponseDto {
    private Integer id;

    private String name;

    private String subjectCode;

    private Boolean active;
}
