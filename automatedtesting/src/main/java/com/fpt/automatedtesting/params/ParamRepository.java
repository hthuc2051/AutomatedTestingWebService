package com.fpt.automatedtesting.params;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParamRepository extends JpaRepository<Param, Integer> {
    List<Param> getAllByActiveIsTrue();
    Param findParamByName(String paramName);
}
