package com.fpt.automatedtesting.submissions;

import com.fpt.automatedtesting.submissions.dtos.request.SubmissionFilesRequest;

import java.util.List;

public interface SubmissionService {
    List<List<String>> getSubmissionFiles(SubmissionFilesRequest request);
}
