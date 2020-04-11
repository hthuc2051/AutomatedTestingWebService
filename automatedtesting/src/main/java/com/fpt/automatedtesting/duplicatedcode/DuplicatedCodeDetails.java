package com.fpt.automatedtesting.duplicatedcode;

import com.fpt.automatedtesting.practicalexams.PracticalExam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "DuplicatedCodeDetails")
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedCodeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "duplicated_code_id", referencedColumnName = "id")
    private DuplicatedCode duplicatedCode;

    @Column(name = "files_token")
    private String filesToken;

}
