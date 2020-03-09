package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.HeadLecturer;
import com.fpt.automatedtesting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeadLecturerRepository extends JpaRepository<HeadLecturer, Integer> {
    Optional<HeadLecturer> findByIdAndActiveIsTrue(Integer id);
    List<HeadLecturer> findAllByActiveIsTrue();
}
