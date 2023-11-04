package com.example.realworld.controller;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.UserDto;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.UpdateUserParam;
import com.example.realworld.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity currentUser(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            @RequestHeader(value = "Authorization") String authorization) {
        UserEntity userData = userService.findById(authUserDetails.getUserId()).get();
        return ResponseEntity.ok(new UserDto(userData, authorization.split(" ")[1]));
    }

    @PutMapping
    public ResponseEntity updateUser(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            @RequestHeader(value = "Authorization") String authorization,
            @Valid @RequestBody UpdateUserParam updateParam
    ) {
        UserEntity userData = userService.findById(authUserDetails.getUserId()).get();
        userService.updateUser(userData, updateParam);

        UserEntity updatedUserData = userService.findById(authUserDetails.getUserId()).get();
        return ResponseEntity.ok(new UserDto(updatedUserData, authorization.split(" ")[1]));
    }
}
