package com.example.realworld.controller;

import com.example.realworld.dto.UserDto;
import com.example.realworld.model.LoginParam;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UserService userService;

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid LoginParam login) {
        log.info("email: {} password:{} ", login.getEmail(), login.getPassword());
        return UserDto.builder().name("name").token("token").build();
    }
}
