package com.example.realworld.service;

import com.example.realworld.domain.dto.UserDto;

public interface UserService {

    UserDto createUser(final UserDto.RegisterParam registerParam);

    UserDto login(UserDto.LoginParam loginParam);
}
