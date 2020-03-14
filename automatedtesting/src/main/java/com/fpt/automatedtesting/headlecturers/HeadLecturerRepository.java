package com.fpt.automatedtesting.headlecturers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeadLecturerRepository extends JpaRepository<HeadLecturer, Integer> {
    Optional<HeadLecturer> findByIdAndActiveIsTrue(Integer id);
    List<HeadLecturer> findAllByActiveIsTrue();
}
