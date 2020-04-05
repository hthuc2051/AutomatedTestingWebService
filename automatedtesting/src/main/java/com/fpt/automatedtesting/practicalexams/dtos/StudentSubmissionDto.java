package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSubmissionDto {
    private String studentCode;
    private String subjectCode;
    private MultipartFile file;
}
