package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedResult {
    // pair StudentA_StudentB
    private String pairStudentToken;
//    Map (Other student line token - result after compute with Cosine)
    private Map<String, Double> resultDetails;
}
