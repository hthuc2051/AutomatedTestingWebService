package com.fpt.automatedtesting.paramtypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamTypeRequestDto {
    private String name;

    private List<String> subjectCode;
}
