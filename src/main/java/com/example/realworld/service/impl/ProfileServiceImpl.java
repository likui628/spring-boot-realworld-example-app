package com.example.realworld.service.impl;

import com.example.realworld.config.AuthUserDetails;
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

        return convertEntityToDto(userEntity, false);
    }

    @Override
    public ProfileDto followByUsername(String username, AuthUserDetails currentUser) {
        UserEntity userEntity = userMapper.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Exception"));

        userMapper.insertFollows(currentUser.getUserId(), userEntity.getId());

        return convertEntityToDto(userEntity, true);
    }

    private ProfileDto convertEntityToDto(UserEntity userEntity, boolean following) {
        return ProfileDto.builder().bio(userEntity.getBio())
                .following(true)
                .image(userEntity.getImage())
                .username(userEntity.getUsername())
                .build();
    }
}
