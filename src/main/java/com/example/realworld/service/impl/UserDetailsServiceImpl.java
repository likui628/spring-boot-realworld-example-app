package com.example.realworld.service.impl;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findByEmail(email)
                .map(userEntity -> AuthUserDetails.builder()
                        .id(userEntity.getId())
                        .email(userEntity.getEmail())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
}
