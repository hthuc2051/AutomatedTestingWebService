package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Integer> {
}
