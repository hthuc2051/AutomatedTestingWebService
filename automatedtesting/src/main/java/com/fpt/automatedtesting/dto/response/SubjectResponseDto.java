package com.fpt.automatedtesting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDto implements Serializable {
    private Integer id;
    private String code;
}
