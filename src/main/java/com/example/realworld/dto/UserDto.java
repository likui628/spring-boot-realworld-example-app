package com.example.realworld.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
@JsonTypeName("user")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class UserDto {

    private String id;

    private String email;

    private String token;

    private String username;

    private String password;

    private String bio;

    private String image;

    @Getter
    @JsonTypeName("user")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterParam {
        @NotBlank(message = "can't be empty")
        @Email(message = "should be an email")
        private String email;

        @NotBlank(message = "can't be empty")
        private String username;

        @NotBlank(message = "can't be empty")
        private String password;
    }
}
