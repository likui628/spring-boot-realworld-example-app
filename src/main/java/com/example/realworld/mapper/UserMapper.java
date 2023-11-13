package com.example.realworld.mapper;

import com.example.realworld.domain.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(@Param("user") UserEntity user);

    UserEntity findByEmail(@Param("email") String email);

    UserEntity findByUsername(@Param("username") String email);

    void insertFollows(@Param("userId") String userId, @Param("followId") String followId);

    void deleteFollows(@Param("userId") String userId, @Param("followId") String followId);

    UserEntity findById(@Param("id") String id);

    void save(@Param("user") UserEntity user);

    boolean isUserFollowing(@Param("userId") String userId, @Param("anotherUserId") String anotherUserId);

    List<String> findFollows(@Param("userId") String userId);
}
