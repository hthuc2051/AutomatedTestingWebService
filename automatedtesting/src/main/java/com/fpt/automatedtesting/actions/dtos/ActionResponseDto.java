package com.fpt.automatedtesting.actions.dtos;

import com.fpt.automatedtesting.params.dtos.ParamResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionResponseDto implements Serializable {

    private Integer id;
    private String name;
    private String code;
    private List<ParamResponseDto> params;
    private List<String> subjectName;
}
