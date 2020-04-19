package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.paramtypes.ParamType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Action_Param")
public class ActionParam {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "param_id", referencedColumnName = "id")
    private Param param;

    @ManyToOne
    @JoinColumn(name = "param_type_id", referencedColumnName = "id")
    private ParamType paramType;
}
