package com.example.todo_security.controller;

import com.example.todo_security.dto.LoginRequestDTO;
import com.example.todo_security.dto.LoginResponseDTO;
import com.example.todo_security.dto.RegisterRequestDto;
import com.example.todo_security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        return loginService.registerUser(registerRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        return loginService.loginUser(loginRequestDTO);
    }
}
