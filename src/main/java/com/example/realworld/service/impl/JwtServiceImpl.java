package com.example.realworld.service.impl;

import com.example.realworld.dto.UserDto;
import com.example.realworld.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtServiceImpl implements JwtService {
    @Override
    public String toToken(UserDto user) {
        return Jwts.builder()
                .setSubject(user.getId())
                .compact();
    }

    @Override
    public Optional<String> getSubFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder().build().parseClaimsJws(token);
        return Optional.ofNullable(claimsJws.getBody().getSubject());
    }
}
