package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.admins.Admin;
import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.scripts.Script;
import com.fpt.automatedtesting.subjects.Subject;
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

    @ManyToMany(targetEntity = Param.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "action_param", joinColumns = {
            @JoinColumn(name = "action_id", referencedColumnName = "id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "param_id", referencedColumnName = "id", nullable = true, updatable = false)})
    private List<Param> params;

    @ManyToMany(targetEntity = Subject.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subject_action", joinColumns = {
            @JoinColumn(name = "action_id", referencedColumnName = "id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = true, updatable = false)})
    private List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    private Admin admin;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;
}
