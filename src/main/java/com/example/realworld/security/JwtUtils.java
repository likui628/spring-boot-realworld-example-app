package com.example.realworld.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;

@Component
@Setter
public class JwtUtils {

    @Value("${realworld.jwt.secretKey}")
    private String secretKey;

    @Value(("${realworld.jwt.tokenPrefix}"))
    private String tokenPrefix;

    @Value("${realworld.jwt.tokenExpirationAfterDays}")
    private Integer tokenExpirationAfterDays;

    public String encode(String sub) {
        return tokenPrefix + Jwts.builder()
                .setSubject(sub)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(
                        LocalDate.now().plusDays(tokenExpirationAfterDays)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(tokenPrefix)) {
            return null;
        }
        return authHeader.substring(tokenPrefix.length());
    }

    public boolean validateToken(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build().parseClaimsJws(jwt);
            Date exp = jws.getBody().getExpiration();
            return exp.after(new Date());
        } catch (JwtException ex) {
            return false;
        }
    }

    public String getSub(String jwt) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build().parseClaimsJws(jwt);
            return jws.getBody().getSubject();
        } catch (JwtException ex) {
            return null;
        }
    }
}
