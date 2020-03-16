package com.fpt.automatedtesting.practicalexams.dtos;

import com.fpt.automatedtesting.submissions.dtos.SubmissionDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticalExamResultDto implements Serializable {
    private Integer id;
    private String state;
    private List<SubmissionDetailsDto> submissions;
}
