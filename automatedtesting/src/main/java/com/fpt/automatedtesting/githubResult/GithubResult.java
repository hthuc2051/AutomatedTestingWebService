package com.fpt.automatedtesting.githubResult;

import com.fpt.automatedtesting.duplicatedcode.DuplicatedCodeDetails;
import com.fpt.automatedtesting.practicalexams.PracticalExam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GithubResult {
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
