package com.example.realworld.domain.dto;

import com.example.realworld.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class UserDto {

    private String id;

    private String email;

    private String token;

    private String username;

    private String bio;

    private String image;

    public UserDto(UserEntity userEntity, String token) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.username = userEntity.getUsername();
        this.bio = userEntity.getBio();
        this.image = userEntity.getImage();
        this.token = token;
    }
}
