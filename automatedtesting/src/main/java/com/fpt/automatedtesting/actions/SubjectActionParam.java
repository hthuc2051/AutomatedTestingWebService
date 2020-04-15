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

    @OneToMany(mappedBy = "subjectActionParam", cascade = CascadeType.ALL)
    private List<SubjectAction> subjectActions;

    @OneToMany(mappedBy = "subjectActionParam", cascade = CascadeType.ALL)
    private List<Param> params;

    @OneToMany(mappedBy = "subjectActionParam", cascade = CascadeType.ALL)
    private List<ParamType> paramTypes;
}
