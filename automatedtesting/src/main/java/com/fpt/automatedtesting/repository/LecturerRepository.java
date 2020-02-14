package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
}
