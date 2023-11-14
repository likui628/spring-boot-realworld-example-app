package com.example.realworld.domain.model;

import com.example.realworld.validation.UniqueSlug;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonTypeName("article")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@AllArgsConstructor
public class UpdateArticleParam {

    @UniqueSlug
    private String title = "";

    private String description = "";

    private String body = "";
}
