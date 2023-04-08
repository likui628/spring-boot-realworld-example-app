package com.example.realworld.service.impl;

import com.example.realworld.dto.UserDto;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.JwtService;
import com.example.realworld.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public UserDto createUser(final UserDto.RegisterParam registerParam) {
        UserDto user = UserDto.builder().email(registerParam.getEmail())
                .id(UUID.randomUUID().toString())
                .username(registerParam.getUsername())
                .password(passwordEncoder.encode(registerParam.getPassword()))
                .bio("")
                .image("")
                .build();
        userMapper.insert(user);
        return user;
    }

    @Override
    public UserDto login(UserDto.LoginParam loginParam) {
        UserDto userDto = userMapper.findByEmail(loginParam.getEmail())
                .filter(user -> passwordEncoder.matches(loginParam.getPassword(), user.getPassword()))
                .orElseThrow(() -> new IllegalStateException("Invalid"));
        userDto.setToken(jwtService.toToken(userDto));
        return userDto;
    }
}
