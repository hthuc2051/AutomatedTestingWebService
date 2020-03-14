package com.fpt.automatedtesting.params;

import lombok.*;
import com.fpt.automatedtesting.actions.Action;
import javax.persistence.*;

@Entity
@Table(name = "Param")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "type", nullable = true )
    private String type;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private Action action;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;
}
