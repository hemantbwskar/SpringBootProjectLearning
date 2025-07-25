package com.springbootproject.learning.controller;

import com.springbootproject.learning.dto.LoginResponse;
import com.springbootproject.learning.dto.LoginUserDto;
import com.springbootproject.learning.dto.RegisterUserDto;
import com.springbootproject.learning.model.UserInfo;
import com.springbootproject.learning.service.AuthenticationService;
import com.springbootproject.learning.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtUtil jwtUtil, AuthenticationService authenticationService) {
        this.jwtUtil = jwtUtil;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Authentication Service is running");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserInfo> signUp(@RequestBody RegisterUserDto dto){
        UserInfo userInfo = authenticationService.signUp(dto);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto dto){
        UserInfo userInfo = authenticationService.authenticate(dto);
        String token = jwtUtil.generateToken(userInfo.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtUtil.getExpiration());
        return ResponseEntity.ok(loginResponse);
    }

}
