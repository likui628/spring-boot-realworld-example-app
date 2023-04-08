package com.example.realworld.service;

import com.example.realworld.dto.UserDto;

import java.util.Optional;

public interface JwtService {

    String toToken(UserDto user);

    Optional<String> getSubFromToken(String token);
}

