package com.fpt.automatedtesting.lecturers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDto {

    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;
}
