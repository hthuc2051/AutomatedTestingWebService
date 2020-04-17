package com.fpt.automatedtesting.githubResult;

import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeRequest;
import com.fpt.automatedtesting.githubResult.dtos.GitHubFileDuplicateDTO;
import com.fpt.automatedtesting.githubResult.dtos.GithubResultDTO;

import java.util.List;
import java.util.Map;

public interface GithubResultService {
    boolean create(int practicalExamId, String studentCode, Map<String, List<GitHubFileDuplicateDTO>> listDuplicate);
    List<GithubResultDTO> getListByPracticalCodeAndStudentCode(DuplicatedCodeRequest request);
}
