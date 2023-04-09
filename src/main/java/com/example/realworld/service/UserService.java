package com.example.realworld.service;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.UserDto;
import com.example.realworld.domain.model.LoginParam;
import com.example.realworld.domain.model.RegisterParam;

public interface UserService {

    UserDto createUser(final RegisterParam registerParam);

    UserDto login(LoginParam loginParam);

    UserDto currentUser(final AuthUserDetails authUserDetails);
}
