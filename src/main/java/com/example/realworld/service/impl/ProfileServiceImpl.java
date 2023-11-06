package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.ProfileDto;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.ProfileService;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {


    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public ProfileDto findByUsername(String username, UserEntity currentUser) {
        return userService
                .findByUsername(username)
                .map(user -> new ProfileDto(user, currentUser != null && userMapper.isUserFollowing(currentUser.getId(), user.getId())))
                .orElseThrow(() -> new IllegalStateException("Exception"));
    }

    @Override
    public ProfileDto followByUsername(String username, UserEntity currentUser) {
        return userService
                .findByUsername(username)
                .map(target -> {
                    userMapper.insertFollows(target.getId(), currentUser.getId());
                    return new ProfileDto(target, true);
                })
                .orElseThrow(() -> new IllegalStateException("Exception"));
    }

    @Override
    public ProfileDto unfollowByUsername(String username, UserEntity currentUser) {
        UserEntity userEntity = userService.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("Exception"));

        userMapper.deleteFollows(userEntity.getId(), currentUser.getId());

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
