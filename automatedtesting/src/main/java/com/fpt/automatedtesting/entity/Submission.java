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

    @Column(name = "script_code")
    private String scriptCode;

    @Column(name = "point")
    private Double point;

    // Student

    @ManyToOne
    @JoinColumn(name = "practical_exam_id", referencedColumnName = "id")
    private PracticalExam practicalExam;

    @ManyToOne
    @JoinColumn(name = "class_student_id", referencedColumnName = "id")
    private ClassStudent classStudent;

    @Column(name = "active")
    private Boolean active;
}
