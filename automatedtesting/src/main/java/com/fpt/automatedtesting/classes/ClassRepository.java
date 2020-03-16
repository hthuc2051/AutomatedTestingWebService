package com.fpt.automatedtesting.classes;

import com.fpt.automatedtesting.classes.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class,Integer> {
    Optional<Class> findByIdAndActiveIsTrue(Integer id);
    List<Class> findAllByActiveIsTrue();
}
