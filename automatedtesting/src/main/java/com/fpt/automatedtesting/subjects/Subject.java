package com.fpt.automatedtesting.subjects;


import com.fpt.automatedtesting.actions.Action;
import com.fpt.automatedtesting.subjectclasses.SubjectClass;
import com.fpt.automatedtesting.scripts.Script;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToMany(targetEntity = Action.class, mappedBy = "subjects", fetch = FetchType.LAZY)
    private List<Action> actions;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Script> scripts;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<SubjectClass> subjectClasses;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

}
