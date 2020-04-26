package com.fpt.automatedtesting.submissions;

import com.fpt.automatedtesting.practicalexams.PracticalExam;
import com.fpt.automatedtesting.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findAllByPracticalExamAndPracticalExam_ActiveAndActiveIsTrue(PracticalExam practicalExam,Boolean active);
    Integer deleteAllByPracticalExam(PracticalExam practicalExam);
    Optional<Submission> findByStudent_CodeAndPracticalExam_CodeAndActiveIsTrue(String studentCode, String examCode);

//    Optional<Submission> findByIdAndActiveIsTrue(Integer id);
//    List<Submission> findAllByActiveIsTrue();
}
