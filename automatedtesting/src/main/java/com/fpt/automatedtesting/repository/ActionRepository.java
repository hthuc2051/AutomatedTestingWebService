package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action,Integer> {
    Optional<Action> findByIdAndActiveIsTrue(Integer id);
    List<Action> findAllByActiveIsTrue();
}
