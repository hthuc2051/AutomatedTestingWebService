package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.PracticalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticalExamRepository extends JpaRepository<PracticalExam,Integer> {
}
