package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.subjects.dtos.SubjectRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionRequestDto {

    private Integer id;
    private String name;
    private String code;
    private SubjectRequestDto subject;
    private List<ActionParameterRequestDto> actionParams;
    private Integer adminId;
    private Boolean active;
}
