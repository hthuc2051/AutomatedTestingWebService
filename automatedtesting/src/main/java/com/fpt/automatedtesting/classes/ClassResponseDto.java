package com.fpt.automatedtesting.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassResponseDto implements Serializable {

    private Integer id;
    private String classCode;
    private Integer subjectClassId;
}
