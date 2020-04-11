package com.fpt.automatedtesting.submissions.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionFilesRequest {
    private String lecturerToken;
    private String filesToken;
}
