package com.example.realworld.controller;

import com.example.realworld.domain.dto.UserDto;
import com.example.realworld.domain.model.LoginParam;
import com.example.realworld.domain.model.RegisterParam;
import com.example.realworld.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path ="/users")
public class UsersController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody RegisterParam registerParam) {
        UserDto user = userService.createUser(registerParam);

        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginParam loginParam){
        UserDto user=userService.login(loginParam);

        return ResponseEntity.status(200).body(user);
    }
}


