package com.fpt.automatedtesting.subjects;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findByIdAndActiveIsTrue(Integer id);
    List<Subject> findAllByActiveIsTrue();
}
