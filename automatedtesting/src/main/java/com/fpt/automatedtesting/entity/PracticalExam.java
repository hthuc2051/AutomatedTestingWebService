package com.fpt.automatedtesting.entity;

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

    @Column(name = "status")
    private String status;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @OneToMany(mappedBy = "practicalExam", cascade = CascadeType.ALL)
    private List<Submission> submissions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_class_id", referencedColumnName = "id")
    private SubjectClass subjectClass;

    @ManyToMany(targetEntity = Script.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "practicalexam_script", joinColumns = {
            @JoinColumn(name = "practical_exam_id", referencedColumnName = "id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "script_id", referencedColumnName = "id", nullable = true, updatable = false)})
    private List<Script> scripts;



}
