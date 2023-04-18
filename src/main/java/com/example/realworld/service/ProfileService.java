package com.example.realworld.service;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.ProfileDto;

public interface ProfileService {

    ProfileDto findByUsername(String username, AuthUserDetails currentUser);

    ProfileDto followByUsername(String username, AuthUserDetails currentUser);

    ProfileDto unfollowByUsername(String username, AuthUserDetails currentUser);
}
