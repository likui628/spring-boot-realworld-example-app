package com.example.realworld.controller;

import com.example.realworld.dto.UserDto;
import com.example.realworld.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@AllArgsConstructor
@RequestMapping(path ="/users")
public class UsersController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody UserDto.RegisterParam registerParam) {
        UserDto user = userService.createUser(registerParam);

        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserDto.LoginParam loginParam){
        UserDto user=userService.login(loginParam);

        return ResponseEntity.status(200).body(user);
    }
}


