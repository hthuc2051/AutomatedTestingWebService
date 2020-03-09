package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.PracticalExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticalExamRepository extends JpaRepository<PracticalExam,Integer> {
    Optional<PracticalExam> findByIdAndActiveIsTrue(Integer id);
    List<PracticalExam> findAllByActiveIsTrue();
}
