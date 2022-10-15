package com.example.realworld.controller;

import com.example.realworld.dto.UserDto;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserDto currentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userService.currentUser(userDetails);
    }
}
