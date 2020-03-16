package com.fpt.automatedtesting.submissions;

import com.fpt.automatedtesting.practicalexams.PracticalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findAllByPracticalExamAndPracticalExam_ActiveAndActiveIsTrue(PracticalExam practicalExam,Boolean active);
//    Optional<Submission> findByIdAndActiveIsTrue(Integer id);
//    List<Submission> findAllByActiveIsTrue();
}
