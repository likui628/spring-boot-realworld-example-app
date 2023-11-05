package com.example.realworld.config;

import com.example.realworld.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {

    private final UserMapper userMapper;

    public Authentication getAuthentication(String userId) {
        return Optional.ofNullable(userId)
                .map(userMapper::findById)
                .map(userEntity ->
                        new UsernamePasswordAuthenticationToken(
                                userEntity,
                                null,
                                Collections.emptyList()
                        ))
                .orElse(null);
    }
}
