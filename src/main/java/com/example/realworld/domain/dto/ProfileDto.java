package com.example.realworld.domain.dto;

import com.example.realworld.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonTypeName("profile")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ProfileDto {

    @JsonIgnore
    private String id;

    private String bio;

    private boolean following;

    private String username;

    private String image;


    public ProfileDto(UserEntity user, boolean following) {
        this.id = user.getId();
        this.bio = user.getBio();
        this.username = user.getUsername();
        this.image = user.getImage();
        this.following = following;
    }
}
