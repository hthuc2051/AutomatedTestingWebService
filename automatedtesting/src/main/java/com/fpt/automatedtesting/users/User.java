package com.fpt.automatedtesting.users;


import com.fpt.automatedtesting.admins.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

}