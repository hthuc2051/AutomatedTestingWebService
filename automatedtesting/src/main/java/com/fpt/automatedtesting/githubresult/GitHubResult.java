package com.fpt.automatedtesting.githubresult;

import com.fpt.automatedtesting.practicalexams.PracticalExam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "GitHubResult")
public class GitHubResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "student_code")
    private String studentCode;

    @Column(name = "result")
    private String result;

    @ManyToOne
    @JoinColumn(name = "practical_exam_id", referencedColumnName = "id")
    private PracticalExam practicalExam;
}
