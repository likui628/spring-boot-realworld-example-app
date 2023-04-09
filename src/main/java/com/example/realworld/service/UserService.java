package com.example.realworld.service;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.UserDto;

public interface UserService {

    UserDto createUser(final UserDto.RegisterParam registerParam);

    UserDto login(UserDto.LoginParam loginParam);

    UserDto currentUser(final AuthUserDetails authUserDetails);
}
