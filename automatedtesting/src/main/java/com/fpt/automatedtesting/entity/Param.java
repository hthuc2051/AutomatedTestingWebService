package com.fpt.automatedtesting.entity;

import lombok.*;
import com.fpt.automatedtesting.entity.Action;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Param")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "type", nullable = true )
    private String type;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private Action action;

}
