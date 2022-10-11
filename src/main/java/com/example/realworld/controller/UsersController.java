package com.example.realworld.controller;

import com.example.realworld.dto.UserDto;
import com.example.realworld.model.LoginParam;
import com.example.realworld.model.RegistrationParam;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UserService userService;

    @PostMapping
    public UserDto registration(@RequestBody @Valid RegistrationParam param) {
        return userService.registration(param);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid LoginParam param) {
        return userService.login(param);
    }
}
