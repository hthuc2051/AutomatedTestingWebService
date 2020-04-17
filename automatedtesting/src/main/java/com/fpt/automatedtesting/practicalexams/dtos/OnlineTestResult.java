package com.fpt.automatedtesting.practicalexams.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineTestResult {
    private String studentCode;
    private AzureTestResult azureTestResult;
}
