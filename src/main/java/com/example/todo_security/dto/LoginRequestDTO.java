package com.example.todo_security.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String usernameOrEmail;
    private String password;
}
