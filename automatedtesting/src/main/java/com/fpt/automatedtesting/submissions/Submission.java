package com.fpt.automatedtesting.submissions;

import com.fpt.automatedtesting.students.Student;
import com.fpt.automatedtesting.practicalexams.PracticalExam;
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

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "practical_exam_id", referencedColumnName = "id")
    private PracticalExam practicalExam;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;
}
