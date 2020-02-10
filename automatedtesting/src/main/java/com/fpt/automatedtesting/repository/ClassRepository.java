package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class,Integer> {
}
