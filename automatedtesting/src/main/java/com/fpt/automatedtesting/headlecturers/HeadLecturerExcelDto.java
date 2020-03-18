package com.fpt.automatedtesting.headlecturers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeadLecturerExcelDto {
    private Integer no;
    private String lecturerCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String contactPhone;
}
