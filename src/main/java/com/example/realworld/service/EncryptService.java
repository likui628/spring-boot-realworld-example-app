package com.example.realworld.service;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean check(String checkPassword, String realPassword) {
        return passwordEncoder.matches(checkPassword, realPassword);
    }

}
