package com.example.realworld.mapper;

import com.example.realworld.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void insert(@Param("user") UserEntity user);

    Optional<UserEntity> findByEmail(@Param("email") String email);

    Optional<UserEntity> findByUsername(@Param("username") String email);

    void insertFollows(@Param("userId") String userId, @Param("followId") String followId);
}
