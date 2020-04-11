package com.fpt.automatedtesting.duplicatedcode;

import com.fpt.automatedtesting.practicalexams.PracticalExam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "DuplicatedCode")
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "practical_exam_id", referencedColumnName = "id")
    private PracticalExam practicalExam;

    @Column(name = "students_token")
    private String studentsToken;

    @Column(name = "similarity_percent")
    private Double similarityPercent;

    @OneToMany(mappedBy = "duplicatedCode", cascade = CascadeType.ALL)
    private List<DuplicatedCodeDetails> duplicatedCodeDetails;

}
