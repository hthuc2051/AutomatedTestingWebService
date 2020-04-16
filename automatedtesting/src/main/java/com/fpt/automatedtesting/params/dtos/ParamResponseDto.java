package com.fpt.automatedtesting.params.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamResponseDto {
    Integer id;
    String name;
    Boolean active;
}
