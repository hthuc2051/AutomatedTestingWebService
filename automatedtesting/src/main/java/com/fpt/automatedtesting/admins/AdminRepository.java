package com.fpt.automatedtesting.admins;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByIdAndActiveIsTrue(Integer id);
    List<Admin> findAllByActiveIsTrue();
}
