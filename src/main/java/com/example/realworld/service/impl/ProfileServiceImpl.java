package com.example.realworld.service.impl;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.ProfileDto;
import com.example.realworld.domain.entity.FollowEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.ProfileService;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {


    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public ProfileDto findByUsername(String username, AuthUserDetails currentUser) {
        UserEntity userEntity = userService.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Exception"));

        final Optional<FollowEntity> follows = userMapper.findFollows(userEntity.getId(), currentUser.getUserId());

        return convertEntityToDto(userEntity, follows.isPresent());
    }

    @Override
    public ProfileDto followByUsername(String username, AuthUserDetails currentUser) {
        UserEntity userEntity = userService.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Exception"));

        userMapper.insertFollows(userEntity.getId(), currentUser.getUserId());

        return convertEntityToDto(userEntity, true);
    }

    @Override
    public ProfileDto unfollowByUsername(String username, AuthUserDetails currentUser) {
        UserEntity userEntity = userService.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Exception"));

        userMapper.deleteFollows(userEntity.getId(), currentUser.getUserId());

        return convertEntityToDto(userEntity, false);
    }

    private ProfileDto convertEntityToDto(UserEntity userEntity, boolean following) {
        return ProfileDto.builder().bio(userEntity.getBio())
                .following(following)
                .image(userEntity.getImage())
                .username(userEntity.getUsername())
                .build();
    }
}
