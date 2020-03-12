package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.SubjectClass;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectClassRepository extends JpaRepository<SubjectClass, Integer> {
    Optional<SubjectClass> findByIdAndActiveIsTrue(Integer id);
    List<SubjectClass> findAllByActiveIsTrue();
    List<SubjectClass> findAllBySubjectId(Integer id);
}
