package com.fpt.automatedtesting.scripts.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionObj {
    private String url;
    private String username;
    private String password;
}
