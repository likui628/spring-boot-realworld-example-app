package com.example.realworld.service;

import com.example.realworld.domain.dto.ProfileDto;

public interface ProfileService {

    ProfileDto findByUsername(String username);
}
