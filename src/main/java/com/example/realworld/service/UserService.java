package com.example.realworld.service;

import com.example.realworld.dto.UserDto;

public interface UserService {

    UserDto createUser(final UserDto.RegisterParam registerParam);
}
