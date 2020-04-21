package com.fpt.automatedtesting.parameters;

import com.fpt.automatedtesting.actions.ActionParameter;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Parameter")
public class Parameter {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @OneToMany(mappedBy = "parameter", cascade = CascadeType.ALL)
    private List<ActionParameter> actionParameters;
}
