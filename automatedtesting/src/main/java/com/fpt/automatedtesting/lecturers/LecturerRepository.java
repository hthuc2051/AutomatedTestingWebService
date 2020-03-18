package com.fpt.automatedtesting.lecturers;

import com.fpt.automatedtesting.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    Lecturer findByUserAndActiveIsTrue(User user);
    Lecturer findByCodeAndActiveIsTrue(String code);
    Optional<Lecturer> findByIdAndActiveIsTrue(Integer id);
    List<Lecturer> findAllByActiveIsTrue();
}
