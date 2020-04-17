package com.fpt.automatedtesting.duplicatedcode.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVectors {
    private String fileName;
    private Map<String, List<Double>> methodVectors;
}
