package com.fpt.automatedtesting.paramtypes;

import com.fpt.automatedtesting.params.ParamResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamTypeResponseDto {
    private Integer id;

    private String name;

    private String subjectCode;

    private Boolean active;

    private List<String> listParams;
}
