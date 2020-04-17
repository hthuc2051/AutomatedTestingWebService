package com.fpt.automatedtesting.params.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamUpdateRequestDto {
    private Integer id;
    private String name;
    private Boolean active;
}
