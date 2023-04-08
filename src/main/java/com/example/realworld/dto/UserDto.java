package com.example.realworld.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    private String password;

    private String bio;

    private String image;

    @Getter
    @JsonTypeName("user")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
    @AllArgsConstructor
    public static class RegisterParam {
        @NotBlank(message = "can't be empty")
        @Email(message = "should be an email")
        private String email;

        @NotBlank(message = "can't be empty")
        private String username;

        @NotBlank(message = "can't be empty")
        private String password;
    }

    @Getter
    @JsonTypeName("user")
    @JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT,use = JsonTypeInfo.Id.NAME)
    @AllArgsConstructor
    public static class LoginParam {
        @NotNull
        @Email
        private String email;

        @NotBlank
        private String password;
    }
}
