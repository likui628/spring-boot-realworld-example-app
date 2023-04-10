package com.example.realworld.domain.model;

import com.example.realworld.validation.UniqueSlug;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@JsonTypeName("article")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@AllArgsConstructor
public class CreateArticleParam {

    @NotBlank(message = "can't be empty")
    @UniqueSlug
    private String title;

    @NotBlank(message = "can't be empty")
    private String description;

    @NotBlank(message = "can't be empty")
    private String body;

    private List<String> tagList;
}
