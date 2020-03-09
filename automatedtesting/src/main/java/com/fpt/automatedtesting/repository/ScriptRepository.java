package com.fpt.automatedtesting.repository;

import com.fpt.automatedtesting.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScriptRepository extends JpaRepository<Script,Integer> {
    Optional<Script> findByIdAndActiveIsTrue(Integer id);
    List<Script> findAllByActiveIsTrue();
}
