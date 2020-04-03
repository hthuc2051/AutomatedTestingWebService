package com.fpt.automatedtesting.subjects.dtos;

import com.fpt.automatedtesting.classes.ClassResponseDto;
import com.fpt.automatedtesting.scripts.dtos.ScriptResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDto implements Serializable {
    private Integer id;
    private String code;
    private List<ScriptResponseDto> scripts;
    private List<ClassResponseDto> classes;

}
