package com.fpt.automatedtesting.practicalexams.dtos;

import com.fpt.automatedtesting.scripts.dtos.ScriptResponseDto;
import com.fpt.automatedtesting.subjectclasses.SubjectClassDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticalExamResponse implements Serializable {

    private Integer id;
    private String code;
    private String date;
    private String subjectCode;
    private Integer subjectId;
    private SubjectClassDto subjectClass;
    private List<ScriptResponseDto> scripts;
    private String classCode;
    private String state;
    private Boolean active;

}
