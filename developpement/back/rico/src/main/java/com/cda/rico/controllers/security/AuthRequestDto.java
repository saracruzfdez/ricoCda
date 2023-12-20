package com.cda.rico.controllers.security;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String gender;
    private String username;
    private String password;
}