package com.fpt.automatedtesting.paramtypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParamTypeRepository extends JpaRepository<ParamType,Integer> {
    List<ParamType> findAllByActiveIsTrue();
    ParamType findByNameAndSubjectCode(String name, String subjectCode);
}
