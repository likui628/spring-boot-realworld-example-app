package com.example.realworld.service;

import com.example.realworld.domain.dto.ProfileDto;
import com.example.realworld.domain.entity.UserEntity;

public interface ProfileService {

    ProfileDto findByUsername(String username, UserEntity currentUser);

    ProfileDto followByUsername(String username, UserEntity currentUser);

    ProfileDto unfollowByUsername(String username, UserEntity currentUser);
}
