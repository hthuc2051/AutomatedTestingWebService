package com.fpt.automatedtesting.githubresult;

import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeRequest;
import com.fpt.automatedtesting.githubresult.dtos.GitHubFileDuplicateDTO;
import com.fpt.automatedtesting.githubresult.dtos.GithubResultDTO;

import java.util.List;
import java.util.Map;

public interface GitHubResultService {
    boolean create(int practicalExamId, String studentCode, Map<String, List<GitHubFileDuplicateDTO>> listDuplicate);
    List<GithubResultDTO> getListByPracticalCodeAndStudentCode(DuplicatedCodeRequest request);
}
