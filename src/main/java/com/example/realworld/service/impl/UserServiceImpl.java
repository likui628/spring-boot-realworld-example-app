package com.example.realworld.service.impl;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.config.JwtService;
import com.example.realworld.domain.dto.UserDto;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.LoginParam;
import com.example.realworld.domain.model.RegisterParam;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private String defaultImage;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public UserServiceImpl(UserMapper userMapper, @Value("${image.default}") String defaultImage, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userMapper = userMapper;
        this.defaultImage = defaultImage;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public UserEntity createUser(final RegisterParam registerParam) {
        UserEntity user = UserEntity.builder()
                .email(registerParam.getEmail())
                .username(registerParam.getUsername())
                .password(passwordEncoder.encode(registerParam.getPassword()))
                .bio("")
                .image(defaultImage)
                .build();
        userMapper.insert(user);
        return user;
    }

    @Override
    public UserEntity login(LoginParam loginParam) {
        return findByEmail(loginParam.getEmail())
                .filter(user -> passwordEncoder.matches(loginParam.getPassword(), user.getPassword()))
                .orElseThrow(() -> new IllegalStateException("Invalid"));
    }

    @Override
    public UserEntity currentUser(AuthUserDetails authUserDetails) {

        return findByEmail(authUserDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("Exception"));
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(userMapper.findByEmail(email));
    }

    @Override
    public Optional<UserEntity> findByUsername(String userName) {
        return Optional.ofNullable(userMapper.findByUsername(userName));
    }

    private UserDto convertEntityToDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .bio(userEntity.getBio())
                .email(userEntity.getEmail())
                .image(userEntity.getImage())
                .token(jwtService.toToken(userEntity))
                .build();
    }
}
