package com.example.realworld.config;

import com.example.realworld.domain.dto.UserDto;
import com.example.realworld.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long expiration;

    public String toToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(getSignKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public String getSub(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    public boolean validateToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token);

        Date now = new Date(System.currentTimeMillis());
        Date expiration = claimsJws.getBody().getExpiration();
        return expiration.after(now);
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}
