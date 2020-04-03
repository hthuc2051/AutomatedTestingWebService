package com.fpt.automatedtesting.params;

import com.fpt.automatedtesting.practicalexams.PracticalExam;
import lombok.*;
import com.fpt.automatedtesting.actions.Action;
import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(targetEntity = Action.class, mappedBy = "params", fetch = FetchType.LAZY)
    private List<Action> actions;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;
}
