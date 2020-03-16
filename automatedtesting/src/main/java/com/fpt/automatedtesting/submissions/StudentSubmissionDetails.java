package com.fpt.automatedtesting.submissions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubmissionDetails {
    private Integer id;
    private String studentName;
    private String studentCode;
    private String scriptCode;
    private String timeSubmitted;
    private String submitPath;
    private Double point;
}
