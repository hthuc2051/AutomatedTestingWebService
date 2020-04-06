package com.fpt.automatedtesting.paramtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fpt.automatedtesting.params.Param;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamType {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "subject_code", nullable = true)
    private String subjectCode;

    @Column(name = "active", nullable = true)
    private Boolean active;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<Param> params;
}
