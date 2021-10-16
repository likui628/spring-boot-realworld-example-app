package com.example.realworld.api.exception;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

import lombok.Getter;

@Getter
@JsonRootName("errors")
@JsonSerialize(using = ErrorResourceSerializer.class)
public class ErrorResource {
    private final List<FieldErrorResource> fieldErrors;

    public ErrorResource(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
