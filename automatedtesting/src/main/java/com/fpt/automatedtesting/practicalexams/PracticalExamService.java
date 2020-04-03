package com.fpt.automatedtesting.practicalexams;

import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamRequest;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamResponse;
import com.fpt.automatedtesting.practicalexams.dtos.PracticalExamResultDto;
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
    List<PracticalExamResponse> getListPracticalExamByLecturer(String enrollKey);
    List<PracticalExamResponse> getPracticalExamsOfLecturer(Integer id);

}
