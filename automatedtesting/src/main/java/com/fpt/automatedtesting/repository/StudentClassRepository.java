package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Class;
import com.fpt.automatedtesting.entity.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass,Integer> {
    List<StudentClass> findAllByClassRoom(Class classRoom);
}
