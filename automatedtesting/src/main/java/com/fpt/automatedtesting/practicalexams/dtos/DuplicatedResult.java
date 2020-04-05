package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DuplicatedResult {
    private String studentCode;
    // Student code line token - Map (Other student token - result after compute with Cosine)
    private Map<String, Map<String,Double>> comparedResult;
    private List<String> checkedTokens;
}
