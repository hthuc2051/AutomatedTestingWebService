package com.fpt.automatedtesting.submissions.dtos;

import com.fpt.automatedtesting.students.StudentReportDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionReport {
    private StudentReportDto student;
    private String scriptCode;
}
