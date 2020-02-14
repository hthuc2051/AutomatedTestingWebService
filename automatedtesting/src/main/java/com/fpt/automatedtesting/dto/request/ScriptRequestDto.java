package com.fpt.automatedtesting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptRequestDto {

    private String name;
    private ArrayList<CodeDto> questions;
    private Integer headLecturerId;
    private Integer subjectId;
}