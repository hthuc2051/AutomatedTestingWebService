package com.fpt.automatedtesting.githubresult.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubResultDTO {
    private String studentFile;
    private List<GitHubFileDuplicateDTO> listFile;
}
