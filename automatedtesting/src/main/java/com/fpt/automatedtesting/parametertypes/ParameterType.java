package com.fpt.automatedtesting.parametertypes;

import com.fpt.automatedtesting.actions.ActionParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ParameterType")
public class ParameterType {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

    @OneToMany(mappedBy = "parameterType", cascade = CascadeType.ALL)
    private List<ActionParameter> actionParameters;

}
