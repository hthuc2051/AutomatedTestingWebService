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
@Table(name = "Class")
public class Class {

    @Id
    private Integer id;

    @Column(name = "class_code")
    private String classCode;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<StudentClass> studentClasses;
}
