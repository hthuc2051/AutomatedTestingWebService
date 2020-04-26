package com.fpt.automatedtesting.students;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentReportDto {
    private String code;
    private String firstName;

    private String middleName;

    private String lastName;
}
