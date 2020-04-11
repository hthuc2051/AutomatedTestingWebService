package com.fpt.automatedtesting.duplicatedcode.dtos;

import com.fpt.automatedtesting.duplicatedcode.DuplicatedCodeDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedCodeResponse {

    private String studentsToken;
    private Double similarityPercent;
    private List<String> duplicatedCodeDetails;
}
