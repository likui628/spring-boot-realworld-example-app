package com.example.realworld.service;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.UserDto;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.LoginParam;
import com.example.realworld.domain.model.RegisterParam;

import java.util.Optional;

public interface UserService {

    UserEntity createUser(final RegisterParam registerParam);

    UserDto login(LoginParam loginParam);

    UserDto currentUser(final AuthUserDetails authUserDetails);

    Optional<UserEntity> findById(String id);
}
