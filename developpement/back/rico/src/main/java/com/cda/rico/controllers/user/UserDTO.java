package com.cda.rico.controllers.user;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class UserDTO {
    private String password;
    private String email;
    private String username;
    private String role;
    private String gender;
}