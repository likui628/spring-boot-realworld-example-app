package com.example.realworld.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonTypeName("article")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class ArticleDto {

    private String id;

    private String slug;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String body;

    private List<String> tagList;

    // TODO ISO 8601 timestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @JsonProperty("author")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
    private ProfileDto profileDto;

    private boolean favorited;

    private int favoritesCount;

}
