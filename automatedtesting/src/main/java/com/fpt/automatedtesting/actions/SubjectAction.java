package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.subjects.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Subject_Action")
public class SubjectAction {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id", nullable = false)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    private Subject subject;

    @OneToMany(mappedBy = "subjectAction", cascade = CascadeType.ALL)
    private List<SubjectActionParam> subjectActionParams;
}
