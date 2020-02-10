package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Submission;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, In> {
}
