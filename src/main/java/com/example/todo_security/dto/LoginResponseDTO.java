package com.example.todo_security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String token;
    private Integer id;
    private String username;
}
