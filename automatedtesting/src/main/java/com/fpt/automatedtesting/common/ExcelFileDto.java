package com.fpt.automatedtesting.common;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelFileDto {
    private String role;
    private MultipartFile excelFile;
}
