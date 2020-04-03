package com.fpt.automatedtesting.scripts;

import com.fpt.automatedtesting.subjects.Subject;
import com.fpt.automatedtesting.headlecturers.HeadLecturer;
import com.fpt.automatedtesting.practicalexams.PracticalExam;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Script")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Script {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = true)
    private String code;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "time_created", nullable = true)
    private String timeCreated;

    @Column(name = "script_path", nullable = true, length = 100)
    private String scriptPath;

    @Column(name = "document_path", nullable = true, length = 100)
    private String documentPath;

    @Column(name = "script_data", nullable = true, length = 100)
    private String scriptData;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "headlecturer_id", referencedColumnName = "id", nullable = false)
    private HeadLecturer headLecturer;

    @ManyToMany(targetEntity = PracticalExam.class, mappedBy = "scripts", fetch = FetchType.LAZY)
    private List<PracticalExam> practicalExams;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;
}
