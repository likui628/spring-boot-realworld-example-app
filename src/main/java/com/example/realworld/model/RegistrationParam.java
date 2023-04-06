package com.example.realworld.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
@Builder
@JsonRootName(value = "user")
public class RegistrationParam {

    @NotNull
    @Pattern(regexp = "[\\w\\d]{1,30}", message = "string contains alphabet or digit with length 1 to 30")
    private String username;

    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 32)
    private String password;
}
