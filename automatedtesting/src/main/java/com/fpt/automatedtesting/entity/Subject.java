package com.fpt.automatedtesting.entity;


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

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private List<Action> actions;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Script> scripts;

    @Column(name = "active")
    private Boolean active;

}
