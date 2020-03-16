package com.fpt.automatedtesting.lecturers;

import com.fpt.automatedtesting.subjectclasses.SubjectClass;
import com.fpt.automatedtesting.users.User;
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

    @Column(name = "enroll_key", nullable = true)
    private String enrollKey;

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
    private List<SubjectClass> subjectClasses;

    @Column(name = "active", columnDefinition = "boolean default true")
    private Boolean active;

}
