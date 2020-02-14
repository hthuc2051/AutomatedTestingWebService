package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Class;
import com.fpt.automatedtesting.entity.ClassStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassStudentRepository extends JpaRepository<ClassStudent,Integer> {
    List<ClassStudent> findAllByClassRoom(Class classRoom);
}
