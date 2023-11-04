package com.example.realworld.domain.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@JsonTypeName("user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserParam {

    @Email(message = "should be an email")
    private String email;

    @Builder.Default
    private String username = "";

    @Builder.Default
    private String password = "";

    @Builder.Default
    private String bio = "";

    @Builder.Default
    private String image = "";
}
