package com.fpt.automatedtesting.practicalexams;

import com.fpt.automatedtesting.duplicatedcode.DuplicatedCode;
import com.fpt.automatedtesting.githubResult.GithubResult;
import com.fpt.automatedtesting.scripts.Script;
import com.fpt.automatedtesting.subjectclasses.SubjectClass;
import com.fpt.automatedtesting.submissions.Submission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "PracticalExam")
@NoArgsConstructor
@AllArgsConstructor
public class PracticalExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    @Column(name = "state")
    private String state;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @OneToMany(mappedBy = "practicalExam", cascade = CascadeType.ALL)
    private List<Submission> submissions;

    @ManyToOne
    @JoinColumn(name = "subject_class_id", referencedColumnName = "id")
    private SubjectClass subjectClass;

    @ManyToMany(targetEntity = Script.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "practicalexam_script", joinColumns = {
            @JoinColumn(name = "practical_exam_id", referencedColumnName = "id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "script_id", referencedColumnName = "id", nullable = true, updatable = false)})
    private List<Script> scripts;

    @OneToMany(mappedBy = "practicalExam", cascade = CascadeType.ALL)
    private List<DuplicatedCode> duplicatedCodes;

    @OneToMany(mappedBy = "practicalExam", cascade = CascadeType.ALL)
    private List<GithubResult> githubResults ;

}
