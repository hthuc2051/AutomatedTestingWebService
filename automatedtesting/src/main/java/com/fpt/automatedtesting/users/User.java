package com.fpt.automatedtesting.users;

import com.fpt.automatedtesting.admins.Admin;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`user`")
@Data
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

    @Column(name = "role")
    private String role;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;
}
