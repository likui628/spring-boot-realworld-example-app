package com.example.realworld.controller;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity currentUser(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return ResponseEntity.status(201)
                .body(userService.currentUser(authUserDetails));
    }
}
