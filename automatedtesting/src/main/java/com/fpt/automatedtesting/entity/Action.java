package com.fpt.automatedtesting.entity;

import lombok.*;

import javax.persistence.*;
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
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

}
