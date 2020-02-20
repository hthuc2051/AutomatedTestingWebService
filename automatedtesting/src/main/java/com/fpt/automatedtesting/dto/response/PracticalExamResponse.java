package com.fpt.automatedtesting.dto.response;

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
    private String status;

}
