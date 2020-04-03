package com.fpt.automatedtesting.subjectclasses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectClassDto implements Serializable {
    private Integer id;
}
