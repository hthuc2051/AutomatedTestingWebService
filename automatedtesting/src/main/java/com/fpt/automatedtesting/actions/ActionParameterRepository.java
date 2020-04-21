package com.fpt.automatedtesting.actions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionParameterRepository extends JpaRepository<ActionParameter, Integer> {
}
