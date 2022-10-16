package com.example.realworld.service;

import com.example.realworld.dto.UserDto;
import com.example.realworld.model.LoginParam;
import com.example.realworld.model.RegistrationParam;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDto registration(RegistrationParam param);

    UserDto login(LoginParam param);

    UserDto currentUser(UserDetails userDetails);

    UserDto updateUser(UserDto userDto, UserDetails userDetails);
}
