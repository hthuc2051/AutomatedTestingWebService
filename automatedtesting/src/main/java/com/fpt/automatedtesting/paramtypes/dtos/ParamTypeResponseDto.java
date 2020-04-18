package com.fpt.automatedtesting.paramtypes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamTypeResponseDto {
    private Integer id;

    private String name;

    private String subjectCode;

    private Boolean active;
}
