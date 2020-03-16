package com.fpt.automatedtesting.students;

import com.fpt.automatedtesting.subjectclasses.SubjectClass;
import com.fpt.automatedtesting.submissions.Submission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student")
public class Student {

    @Id
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Submission> submissions;

    @ManyToMany(targetEntity = SubjectClass.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_class_student", joinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "subject_class_id", referencedColumnName = "id", nullable = true, updatable = false)})
    private List<SubjectClass> subjectClasses;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;
}
