package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.admins.Admin;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Action")
public class Action {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @OneToMany(mappedBy = "action",cascade = CascadeType.ALL)
    private List<SubjectAction> subjectActions;
}
