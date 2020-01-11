package com.fpt.automatedtesting.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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

    @Column(name = "timeCreated", nullable = true)
    private Date timeCreated;

    @Column(name = "scriptPath", nullable = true, length = 100)
    private String scriptPath;

}
