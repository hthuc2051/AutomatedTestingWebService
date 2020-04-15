package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.params.Param;
import com.fpt.automatedtesting.paramtypes.ParamType;
import com.fpt.automatedtesting.subjects.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Subject_Action_Param")
public class SubjectActionParam {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subject_action_id", referencedColumnName = "id")
    private SubjectAction subjectAction;

    @ManyToOne
    @JoinColumn(name = "param_id", referencedColumnName = "id")
    private Param param;

    @ManyToOne
    @JoinColumn(name = "param_type_id", referencedColumnName = "id")
    private ParamType paramType;
}
