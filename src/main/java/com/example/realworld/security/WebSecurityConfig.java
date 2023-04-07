package com.example.realworld.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/users/login")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/tags")
                .permitAll();
        return http.build();
    }
}
