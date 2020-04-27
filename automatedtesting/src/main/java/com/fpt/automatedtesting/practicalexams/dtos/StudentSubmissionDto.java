package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubmissionDto {
    private String studentCode;
    private String examCode;
    private String submitTime;
    private String point;
    private MultipartFile file;
    private MultipartFile webFile;
    private File project;

}
