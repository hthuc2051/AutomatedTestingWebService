package com.fpt.automatedtesting.practicalexams;

import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeRequest;
import com.fpt.automatedtesting.duplicatedcode.dtos.DuplicatedCodeResponse;
import com.fpt.automatedtesting.practicalexams.dtos.*;
import com.fpt.automatedtesting.submissions.StudentSubmissionDetails;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PracticalExamService {

    String create(PracticalExamRequest dto);

    String update(PracticalExamRequest dto);

    Boolean updatePracticalExamResult(PracticalExamResultDto dto);

    void downloadPracticalTemplate(Integer id, HttpServletResponse response);

    String delete(Integer id);

    List<StudentSubmissionDetails> getListStudentInPracticalExam(Integer id);

    List<PracticalExamResponse> getPracticalExamsOfSubject(Integer id);
    List<PracticalExamResponse> enrollPracticalExam(String enrollKey);
    List<PracticalExamResponse> getPracticalExamsOfLecturer(Integer id);

    String getStudentSubmission(StudentSubmissionDto dto);

    String checkDuplicatedCode(PracticalInfo info);

    List<DuplicatedCodeResponse> getDuplicatedResult(DuplicatedCodeRequest practicalInfo);
    List<OnlineTestResult> getResultFromAzure(Integer code);

    String test();
}
