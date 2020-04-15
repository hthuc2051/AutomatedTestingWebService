package com.fpt.automatedtesting.practicalexams.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubResponseDTO implements Serializable {
    private int total_count;
    private ArrayList<GitHubFileDuplicateDTO> items;
}
