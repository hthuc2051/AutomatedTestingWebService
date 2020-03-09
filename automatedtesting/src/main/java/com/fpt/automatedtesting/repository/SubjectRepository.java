package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findByIdAndActiveIsTrue(Integer id);
    List<Subject> findAllByActiveIsTrue();
}
