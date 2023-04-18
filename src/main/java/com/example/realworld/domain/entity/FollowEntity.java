package com.example.realworld.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowEntity {

    private String userId;

    private String followId;
}
