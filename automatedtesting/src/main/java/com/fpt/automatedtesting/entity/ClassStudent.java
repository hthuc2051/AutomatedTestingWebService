package com.fpt.automatedtesting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Class_Student")
public class ClassStudent {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private Class classRoom;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;

    @OneToMany(mappedBy = "classStudent", cascade = CascadeType.ALL)
    private List<Submission> submissions;

}
