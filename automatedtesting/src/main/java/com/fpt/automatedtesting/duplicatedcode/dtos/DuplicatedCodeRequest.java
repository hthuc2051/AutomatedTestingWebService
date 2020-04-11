package com.fpt.automatedtesting.duplicatedcode.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedCodeRequest {

    private String practicalExamCode;
    private String studentCode;
}
