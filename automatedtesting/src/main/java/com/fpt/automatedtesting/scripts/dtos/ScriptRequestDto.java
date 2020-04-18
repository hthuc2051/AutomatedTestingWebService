package com.fpt.automatedtesting.scripts.dtos;

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
    private String globalVariable;
    private String questions;
    private Integer headLecturerId;
    private Integer subjectId;
    private String scriptData;
    private MultipartFile docsFile;
    private MultipartFile database;
    private MultipartFile templateQuestion;
    private String onlineConnection;
    private String offlineConnection;
}