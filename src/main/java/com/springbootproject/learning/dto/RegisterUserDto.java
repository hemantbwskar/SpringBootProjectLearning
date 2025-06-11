package com.springbootproject.learning.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterUserDto {

    private String email;
    private String password;
    private String fullName;
}
