package com.fpt.automatedtesting.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`User`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 500)
    private String username;

    @Column(name = "password", length = 500)
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Admin> admins;



    @ManyToMany(targetEntity = Script.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_script", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = true)}, inverseJoinColumns = {
            @JoinColumn(name = "script_id", referencedColumnName = "id", nullable = true, updatable = false)})
    private List<Script> scripts;
}