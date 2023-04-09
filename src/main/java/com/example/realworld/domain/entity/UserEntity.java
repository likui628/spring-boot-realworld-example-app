package com.example.realworld.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class UserEntity {

    private String id;

    private String email;

    private String username;

    private String password;

    private String bio;

    private String image;

    @Builder
    public UserEntity(String email, String username, String password, String bio, String image) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }
}
