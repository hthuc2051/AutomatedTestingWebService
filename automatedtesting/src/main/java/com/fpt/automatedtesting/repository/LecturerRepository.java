package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Lecturer;
import com.fpt.automatedtesting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    Lecturer findByUserAndActiveIsTrue(User user);
}
