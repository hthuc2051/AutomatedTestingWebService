package com.fpt.automatedtesting.practicalexams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticalExamRepository extends JpaRepository<PracticalExam,Integer> {
    Optional<PracticalExam> findByIdAndActiveIsTrue(Integer id);
    Optional<PracticalExam> findByCodeAndActiveIsTrue(String id);
    Optional<PracticalExam> findByIdAndStateEqualsAndActiveIsTrue(Integer id, String state);
    List<PracticalExam> findAllByActiveIsTrue();
}
