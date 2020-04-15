package com.fpt.automatedtesting.actions;

import com.fpt.automatedtesting.subjects.Subject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Subject_Action")
public class SubjectAction {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "subjectAction", cascade = CascadeType.ALL)
    private List<Action> actions;

    @OneToMany(mappedBy = "subjectAction", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    @ManyToOne
    @JoinColumn(name = "subject_action_id", referencedColumnName = "id", nullable = false)
    private SubjectActionParam subjectActionParam;
}
