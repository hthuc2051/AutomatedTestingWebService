package com.fpt.automatedtesting.submissions;

import com.fpt.automatedtesting.submissions.dtos.request.SubmissionFilesRequest;

import java.util.List;
import java.util.Map;

public interface SubmissionService {
    Map<String,List<String>> getSubmissionFiles(SubmissionFilesRequest request);
}
