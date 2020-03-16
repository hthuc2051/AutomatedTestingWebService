package com.fpt.automatedtesting.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptResponseDto {

    private Integer id;
    private Date timeCreated;
    private String scriptCode;

}