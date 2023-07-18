package com.example.todo_security.service;

import com.example.todo_security.dto.LoginRequestDTO;
import com.example.todo_security.dto.LoginResponseDTO;
import com.example.todo_security.dto.RegisterRequestDto;
import com.example.todo_security.model.User;
import com.example.todo_security.model.UserDetailImpl;
import com.example.todo_security.repository.UserRespository;
import com.example.todo_security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Date;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> registerUser(RegisterRequestDto registerRequestDto) {
        User user = User.builder().username(registerRequestDto.getUsername()).password(registerRequestDto.getPassword()).build();
        User savedUser = null;
        ResponseEntity<String> response = null;
        try {
            String hashPwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashPwd);
            savedUser = userRespository.save(user);
            if (savedUser.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }

    public ResponseEntity<LoginResponseDTO> loginUser( LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsernameOrEmail(), loginRequestDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
            System.out.println(userDetail);
            String name = loginRequestDTO.getUsernameOrEmail();
            String token = jwtUtils.generateJwtToken(authentication);
            String role = userDetail.getAuthorities().toArray()[0].toString().equals("ROLE_ADMIN")?"Administrator":"Customer";
            System.out.println(role);
            return ResponseEntity.ok(LoginResponseDTO.builder().token(token).id(userDetail.getId()).username(name).build());
        }catch (Exception ex) {
            throw  ex;
        }

    }
}
