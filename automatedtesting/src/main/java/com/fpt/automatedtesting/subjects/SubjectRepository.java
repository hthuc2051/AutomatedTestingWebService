package com.fpt.automatedtesting.subjects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findByIdAndActiveIsTrue(Integer id);
    List<Subject> findAllByActiveIsTrue();
}
