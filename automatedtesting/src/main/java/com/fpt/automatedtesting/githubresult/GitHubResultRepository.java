package com.fpt.automatedtesting.githubresult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubResultRepository extends JpaRepository<GitHubResult,Integer> {
    GitHubResult findByPracticalExamCodeAndStudentCode(String practicalExamCode, String studentCode );
}
