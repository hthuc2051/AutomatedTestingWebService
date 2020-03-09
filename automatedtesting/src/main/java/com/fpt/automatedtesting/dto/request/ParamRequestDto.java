package com.fpt.automatedtesting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParamRequestDto implements Serializable {
    private Integer id;
    private String name;
    private String type;
    private Boolean active = true;
}
