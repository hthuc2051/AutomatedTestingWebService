package com.fpt.automatedtesting.users;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "`user`")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String role;
    private Boolean active;
}
