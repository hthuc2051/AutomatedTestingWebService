package com.fpt.automatedtesting.users.dtos;

import lombok.Data;


@Data
public class LoginResponse {

    private Integer id;
    private String accessToken;
    private String role;

    public LoginResponse(Integer id,String accessToken, String role) {
        this.id = id;
        this.accessToken = accessToken;
        this.role = role;

    }
}
