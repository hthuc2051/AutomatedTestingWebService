package com.fpt.automatedtesting.practicalexams.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RunTestDto {
    private String url;
    private Integer totalTests;
    private Integer passedTests;
    private Integer unanalyzedTests;
}
