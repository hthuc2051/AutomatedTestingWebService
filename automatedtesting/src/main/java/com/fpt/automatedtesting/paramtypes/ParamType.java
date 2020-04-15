package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.actions.SubjectAction;
import com.fpt.automatedtesting.actions.SubjectActionParam;
import com.fpt.automatedtesting.params.Param;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ParamType")
public class ParamType {

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

    @OneToMany(mappedBy = "paramType", cascade = CascadeType.ALL)
    private List<SubjectActionParam> subjectActionParams;

}
