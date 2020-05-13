package com.fpt.automatedtesting.duplicatedcode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DuplicatedCodeRepository extends JpaRepository<DuplicatedCode, Integer> {
    List<DuplicatedCode> findByStudentsTokenContainingAndPracticalExam_Code(String token, String examCode);
    List<DuplicatedCode> findByStudentsToken(String s);
}
