package com.fpt.automatedtesting.parameters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
    List<Parameter> getAllByActiveIsTrue();
    Parameter findParamByName(String paramName);
}
