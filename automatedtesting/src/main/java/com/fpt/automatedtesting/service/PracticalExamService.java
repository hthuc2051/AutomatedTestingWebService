package com.fpt.automatedtesting.service;

import com.fpt.automatedtesting.dto.request.EnrollDetailsDto;
import com.fpt.automatedtesting.dto.request.PracticalExamRequest;
import com.fpt.automatedtesting.dto.request.PracticalExamResultDto;
import com.fpt.automatedtesting.dto.response.PracticalExamResponse;
import com.fpt.automatedtesting.dto.response.StudentSubmissionDetails;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PracticalExamService {

    String create(PracticalExamRequest dto);

    Boolean updatePracticalExamResult(PracticalExamResultDto dto);

    void downloadPracticalTemplate(Integer id, HttpServletResponse response);

    String delete(Integer id);

    List<StudentSubmissionDetails> getListStudentInPracticalExam(Integer id);

    List<PracticalExamResponse> getPracticalExamsOfSubject(Integer id);
    List<PracticalExamResponse> getListPracticalExamByLecturer(String enrollKey);
    List<PracticalExamResponse> getPracticalExamsOfLecturer(Integer id);

}
