package com.fpt.automatedtesting.dto.response;

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

}
