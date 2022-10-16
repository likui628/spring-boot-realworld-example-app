package com.example.realworld.controller;

import com.example.realworld.dto.UserDto;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserDto currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.currentUser(userDetails);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto userDto, @AuthenticationPrincipal UserDetails userDetails) {
        return userService.updateUser(userDto, userDetails);
    }

}
