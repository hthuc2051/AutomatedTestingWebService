package com.fpt.automatedtesting.parametertypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterTypeRepository extends JpaRepository<ParameterType,Integer> {
    List<ParameterType> findAllByActiveIsTrue();
    ParameterType findByNameAndSubjectCode(String name, String subjectCode);
    List<ParameterType> findAllBySubjectCodeAndActiveIsTrue(String subjectCode);
}
