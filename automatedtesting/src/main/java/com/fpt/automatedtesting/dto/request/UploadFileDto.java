package com.fpt.automatedtesting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileDto {
    private String studentCode;
    private MultipartFile file;
}
