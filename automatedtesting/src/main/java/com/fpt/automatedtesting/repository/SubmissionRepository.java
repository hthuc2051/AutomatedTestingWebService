package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.PracticalExam;
import com.fpt.automatedtesting.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findAllByPracticalExamActiveIsTrue(PracticalExam practicalExam);
    Optional<Submission> findByIdAndActiveIsTrue(Integer id);
    List<Submission> findAllByActiveIsTrue();
}
