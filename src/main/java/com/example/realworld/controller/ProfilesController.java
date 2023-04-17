package com.example.realworld.controller;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/profiles/{username}")
@RequiredArgsConstructor
public class ProfilesController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity getArticles(@PathVariable("username") String username,
                                      @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        //todo following
        return ResponseEntity.status(200).body(profileService.findByUsername(username));
    }
}
