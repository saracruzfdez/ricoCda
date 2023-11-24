package com.cda.rico.controllers.user;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class UserDTO {
    private String name;
    private String email;
}
