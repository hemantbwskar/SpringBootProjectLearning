package com.springbootproject.learning.service;

import com.springbootproject.learning.dto.LoginUserDto;
import com.springbootproject.learning.dto.RegisterUserDto;
import com.springbootproject.learning.model.UserInfo;
import com.springbootproject.learning.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserInfo signUp(RegisterUserDto dto){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(dto.getEmail());
        userInfo.setPassword(passwordEncoder.encode(dto.getPassword()));
        userInfo.setFullName(dto.getFullName());

        // Check if the user already exists
        UserInfo existingUser = userInfoRepository.findByEmail(dto.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User already exists with email: " + dto.getEmail());
        }

        return userInfoRepository.save(userInfo);
    }

    public UserInfo authenticate(LoginUserDto dto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        return userInfoRepository.findByEmail(dto.getEmail());
    }
}
