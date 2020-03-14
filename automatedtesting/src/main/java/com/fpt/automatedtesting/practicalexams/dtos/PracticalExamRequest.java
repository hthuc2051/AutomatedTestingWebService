package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticalExamRequest implements Serializable {

    private Integer id;
    private List<Integer> listScripts;
    private List<Integer> subjectClasses;
    private String date;

}
