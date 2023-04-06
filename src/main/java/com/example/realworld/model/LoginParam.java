package com.example.realworld.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@Builder
@JsonRootName(value = "user")
public class LoginParam {
    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 32)
    private String password;
}
