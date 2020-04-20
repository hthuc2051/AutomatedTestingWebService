package com.fpt.automatedtesting.paramtypes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamTypeForActionDto {
    private Integer id;
    private String name;
    private String subjectCode;
    private Boolean active;
}
