package com.fpt.automatedtesting.githubresult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubResultRepository extends JpaRepository<GithubResult,Integer> {
    GithubResult findByPracticalExamCodeAndStudentCode(String practicalExamCode,String studentCode );
}