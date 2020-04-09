package com.fpt.automatedtesting.paramtypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamTypeUpdateRequestDto {
    private Integer id;

    private String name;

    private List<String> subjectCodes;

    private Boolean active;
}
