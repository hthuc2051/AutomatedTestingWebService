package com.fpt.automatedtesting.practicalexams.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineTestResult {
    private String studentCode;
    private List<AzureTestResult> azureTestResult;
}
