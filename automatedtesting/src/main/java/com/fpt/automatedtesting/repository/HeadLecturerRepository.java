package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.HeadLecturer;
import com.fpt.automatedtesting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadLecturerRepository extends JpaRepository<HeadLecturer, Integer> {
}
