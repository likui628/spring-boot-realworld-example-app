package com.example.realworld.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@JsonRootName(value = "user")
public class UserDto {

    private String email;

    private String token;

    private String username;

    private String bio;

    private String image;
}
