package com.example.realworld.domain.entity;

import com.google.common.base.Strings;
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
    
    public void update(String email, String username, String password, String bio, String image) {
        if (!Strings.isNullOrEmpty(email)) {
            this.email = email;
        }

        if (!Strings.isNullOrEmpty(username)) {
            this.username = username;
        }

        if (!Strings.isNullOrEmpty(password)) {
            this.password = password;
        }

        if (!Strings.isNullOrEmpty(bio)) {
            this.bio = bio;
        }

        if (!Strings.isNullOrEmpty(image)) {
            this.image = image;
        }
    }
}
