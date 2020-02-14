package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.dto.response.StudentSubmissionDetails;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PracticalExamService {

    Boolean create(PracticalExamRequest dto);
    void downloadPracticalTemplate(Integer id,HttpServletResponse response);
    List<StudentSubmissionDetails> getListStudentInPracticalExam(Integer id);
}
