package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.subjects.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActionRepository extends JpaRepository<Action,Integer> {
    Optional<Action> findByIdAndActiveIsTrue(Integer id);
    List<Action> findAllByActiveIsTrue();
    @Query("SELECT a FROM Action a INNER JOIN a.subjects s WHERE s.id=?1")
    List<Action> findAllBySubjectAndActiveIsTrue(Integer subjectId);
}
