package com.fpt.automatedtesting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptRequestDto {
    private int id;
    private String name;
    private String questionPointStr;
    private String questions;
    private Integer headLecturerId;
    private Integer subjectId;
    private MultipartFile docsFile;
}