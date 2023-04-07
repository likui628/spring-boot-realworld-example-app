package com.example.realworld.service.impl;

import com.example.realworld.dto.UserDto;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserDto createUser(final UserDto.RegisterParam registerParam) {
        UserDto user = UserDto.builder().email(registerParam.getEmail())
                .id(UUID.randomUUID().toString())
                .username(registerParam.getUsername())
                .bio("")
                .image("")
                .build();
        userMapper.insert(user);
        return user;
    }
}
