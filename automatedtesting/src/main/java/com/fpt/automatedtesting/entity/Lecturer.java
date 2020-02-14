package com.fpt.automatedtesting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lecturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "middle_name", nullable = true)
    private String middleName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "contact_phone", nullable = true)
    private String contactPhone;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private List<PracticalExam> practicalExams;

    @Column(name = "active", nullable = true)
    private Boolean active;

}
