package com.fpt.automatedtesting.submissions.dtos.response;

import com.fpt.automatedtesting.students.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponse {
    private Integer id;
    private StudentDto student;
    private String scriptCode;
    private String date;
    private Boolean evaluatedOnline;
}
