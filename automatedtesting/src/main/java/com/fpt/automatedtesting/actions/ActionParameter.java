package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.parameters.Parameter;
import com.fpt.automatedtesting.parametertypes.ParameterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Action_Parameter")
public class ActionParameter {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "parameter_id", referencedColumnName = "id")
    private Parameter parameter;

    @ManyToOne
    @JoinColumn(name = "parameter_type_id", referencedColumnName = "id")
    private ParameterType parameterType;
}
