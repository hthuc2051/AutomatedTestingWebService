package com.fpt.automatedtesting.githubresult.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubFileDuplicateDTO implements Serializable {
    private String name;
    private String html_url;
    private int score;
    private double percent;
}
