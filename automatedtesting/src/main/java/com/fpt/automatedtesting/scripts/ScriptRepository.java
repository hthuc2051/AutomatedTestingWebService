package com.fpt.automatedtesting.scripts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScriptRepository extends JpaRepository<Script,Integer> {
    Optional<Script> findByIdAndActiveIsTrue(Integer id);
    List<Script> findAllByActiveIsTrue();
    List<Script> getAllBySubjectId(Integer id);
}
