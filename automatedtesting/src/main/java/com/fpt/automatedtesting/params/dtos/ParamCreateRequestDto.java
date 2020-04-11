package com.fpt.automatedtesting.params.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamCreateRequestDto {
    private String name;
    private Integer typeId;
    private Boolean active;
}
