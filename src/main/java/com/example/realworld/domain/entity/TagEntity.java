package com.example.realworld.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TagEntity {

    private String id;

    private String name;

    @Builder
    public TagEntity(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
