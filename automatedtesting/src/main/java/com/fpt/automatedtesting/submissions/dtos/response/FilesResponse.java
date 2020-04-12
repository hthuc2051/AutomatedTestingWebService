package com.fpt.automatedtesting.submissions.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesResponse {
    private String fileName;
    private Integer startLineHighLight;
    private Integer endLineHighLight;
    private List<String> code;
}
