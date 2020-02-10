package com.fpt.automatedtesting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Submission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_submitted")
    private String timeSubmitted;

    @Column(name = "submit_path")
    private String submitPath;

    @Column(name = "mark")
    private Double mark;

    // Student

    @ManyToOne
    @JoinColumn(name = "practical_exam_id", referencedColumnName = "id")
    private PracticalExam practicalExam;

    @ManyToOne
    @JoinColumn(name = "student_class_id", referencedColumnName = "id")
    private StudentClass studentClass;

    @Column(name = "active")
    private Boolean active;
}
