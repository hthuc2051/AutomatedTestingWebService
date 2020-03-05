package com.fpt.automatedtesting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDetailsDto {
    private Integer id;
    private String timeSubmitted;
    private String submitPath;
    private Double point;
}
