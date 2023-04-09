package com.example.realworld.service.impl;

import com.example.realworld.config.JwtService;
import com.example.realworld.domain.dto.UserDto;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.mapper.UserMapper;
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
        UserEntity userEntity = userMapper.findByEmail(loginParam.getEmail())
                .filter(user -> passwordEncoder.matches(loginParam.getPassword(), user.getPassword()))
                .orElseThrow(() -> new IllegalStateException("Invalid"));

        return convertEntityToDto(userEntity);
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .bio(userEntity.getBio())
                .email(userEntity.getEmail())
                .image(userEntity.getImage())
                .token(jwtService.toToken(userEntity))
                .build();
    }
}
