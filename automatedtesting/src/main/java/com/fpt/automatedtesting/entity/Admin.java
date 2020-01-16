package com.fpt.automatedtesting.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`Admin`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "email", length = 500)
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Action> actions;

}