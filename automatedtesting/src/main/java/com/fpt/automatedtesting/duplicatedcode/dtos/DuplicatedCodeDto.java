package com.fpt.automatedtesting.duplicatedcode.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuplicatedCodeDto {
    private String studentCode;
    private List<FileVectors> studentFileVectors;

}
