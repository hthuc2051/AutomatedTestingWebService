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

    @OneToMany(mappedBy = "practicalExam", cascade = CascadeType.ALL)
    private List<Submission> submissions;

    @ManyToMany(targetEntity = Script.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "practicalexam_script", joinColumns = {
            @JoinColumn(name = "practical_exam_id", referencedColumnName = "id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "script_id", referencedColumnName = "id", nullable = true, updatable = false)})
    private List<Script> scripts;

    @Column(name = "active")
    private Boolean active;

}
