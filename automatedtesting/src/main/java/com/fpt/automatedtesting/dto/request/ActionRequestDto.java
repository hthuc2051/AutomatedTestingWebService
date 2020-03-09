package com.fpt.automatedtesting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionRequestDto implements Serializable {

    private Integer id;
    private String name;
    private String code;
    private List<ParamRequestDto> params;
    private Integer adminId;
    private int subjectId;
    private boolean active;
}
