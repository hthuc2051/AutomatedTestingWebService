package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, Integer> {

}
