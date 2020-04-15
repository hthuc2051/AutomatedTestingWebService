package com.fpt.automatedtesting.githubResult;

import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeRequest;
import com.fpt.automatedtesting.githubResult.dtos.GitHubFileDuplicateDTO;

import java.util.List;
import java.util.Map;

public interface GithubResultService {
    boolean create(int practicalExamId, String studentCode, Map<String, List<GitHubFileDuplicateDTO>> listDuplicate);
    Map<String, List<GitHubFileDuplicateDTO>> getListByPracticalCodeAndStudentCode(DuplicatedCodeRequest request);
}
