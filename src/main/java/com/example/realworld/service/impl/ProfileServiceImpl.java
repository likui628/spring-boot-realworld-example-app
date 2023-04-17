package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.ProfileDto;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {


    private final UserMapper userMapper;

    @Override
    public ProfileDto findByUsername(String username) {
        UserEntity userEntity = userMapper.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Exception"));
        return ProfileDto.builder().bio(userEntity.getBio())
                .following(false)
                .image(userEntity.getImage())
                .username(userEntity.getUsername())
                .build();
    }
}
