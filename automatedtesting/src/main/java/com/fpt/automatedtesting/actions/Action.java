package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.admins.Admin;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id", nullable = false)
    private SubjectAction subjectAction;
}
