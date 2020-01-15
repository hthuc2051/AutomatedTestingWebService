package com.fpt.automatedtesting.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Action")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "code", nullable = true)
    private String code;

    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL)
    private List<Param> params;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    private Admin admin;

}
