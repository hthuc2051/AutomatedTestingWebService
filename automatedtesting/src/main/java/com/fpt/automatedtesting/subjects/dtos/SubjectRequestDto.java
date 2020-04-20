package com.fpt.automatedtesting.subjects.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequestDto {

    private Integer id;

    private String name;

    private String code;

    private Boolean active;
}
