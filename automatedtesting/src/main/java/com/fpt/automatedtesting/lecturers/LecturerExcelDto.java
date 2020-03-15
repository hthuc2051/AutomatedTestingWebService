package com.fpt.automatedtesting.lecturers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LecturerExcelDto {
    private Integer no;
    private String studentCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
}
