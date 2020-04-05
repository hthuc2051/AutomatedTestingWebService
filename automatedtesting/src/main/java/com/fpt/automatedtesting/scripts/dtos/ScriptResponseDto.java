package com.fpt.automatedtesting.scripts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptResponseDto implements Serializable {
    private Integer id;
    private String name;
    private String timeCreated;
    private String code;
    private String scriptData;
    private String docFileName;
}