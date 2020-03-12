package com.fpt.automatedtesting.dto.response;

import com.fpt.automatedtesting.entity.SubjectClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassResponseDto implements Serializable {

    private Integer id;
    private String classCode;
    private Integer subjectClassId;
}
