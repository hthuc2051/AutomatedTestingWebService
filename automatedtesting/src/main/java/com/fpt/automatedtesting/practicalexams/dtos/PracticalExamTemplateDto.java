package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticalExamTemplateDto {

    private String serverSubject;
    private MultipartFile serverFile;
    private String scriptSubject;
    private MultipartFile scriptFile;

}
