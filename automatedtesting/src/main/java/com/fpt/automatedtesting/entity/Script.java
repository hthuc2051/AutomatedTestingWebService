package com.fpt.automatedtesting.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Script")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Script {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = true)
    private String code;

    @Column(name = "time_created", nullable = true)
    private String timeCreated;

    @Column(name = "script_type", nullable = true)
    private String scriptType;

    @Column(name = "script_path", nullable = true, length = 100)
    private String scriptPath;

    @ManyToMany(targetEntity = PracticalExam.class, mappedBy = "scripts", fetch = FetchType.LAZY)
    private List<PracticalExam> practicalExams;

    @ManyToMany(targetEntity = User.class, mappedBy = "scripts", fetch = FetchType.LAZY)
    private List<User> users;

}
