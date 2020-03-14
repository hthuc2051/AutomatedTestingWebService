package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticalExamResponse implements Serializable {

    private Integer id;
    private String code;
    private String date;
    private String subjectCode;
    private Integer subjectId;
    private String classCode;
    private String state;

}
