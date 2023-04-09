package com.example.realworld.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserEntity {

    private String id;

    private String email;

    private String username;

    private String password;

    private String bio;

    private String image;
}
