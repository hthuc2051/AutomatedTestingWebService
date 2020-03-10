package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.PracticalExam;
import com.fpt.automatedtesting.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findAllByPracticalExamAndPracticalExam_ActiveAndActiveIsTrue(PracticalExam practicalExam,Boolean active);
//    Optional<Submission> findByIdAndActiveIsTrue(Integer id);
//    List<Submission> findAllByActiveIsTrue();
}
