package com.fpt.automatedtesting.params.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamTypeDTO {
    Integer id;
    String name;
    String type;
}
