package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.admins.Admin;
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

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    private Admin admin;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;


    @OneToMany(mappedBy = "action",cascade = CascadeType.ALL)
    private List<SubjectAction> subjectActions;
}
