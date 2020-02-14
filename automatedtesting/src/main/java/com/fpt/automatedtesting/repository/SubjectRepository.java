package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
