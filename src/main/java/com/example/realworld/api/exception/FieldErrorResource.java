package com.example.realworld.api.exception;

import lombok.Getter;

@Getter
public class FieldErrorResource {
    private String resource;
    private String field;
    private String code;
    private String message;

    public FieldErrorResource(String resouce, String field, String code, String message) {
        this.resource = resouce;
        this.field = field;
        this.code = code;
        this.message = message;
    }

}