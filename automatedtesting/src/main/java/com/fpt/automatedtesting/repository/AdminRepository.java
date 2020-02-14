package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
