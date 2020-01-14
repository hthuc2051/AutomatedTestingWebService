package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Action;
import com.fpt.automatedtesting.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action,Integer> {
}
