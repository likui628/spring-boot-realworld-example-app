package com.example.realworld.controller;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/profiles/{username}")
@RequiredArgsConstructor
public class ProfilesController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity getProfiles(@PathVariable("username") String username,
                                      @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        //todo following
        return ResponseEntity.ok(profileService.findByUsername(username));
    }

    @PostMapping("/follow")
    public ResponseEntity followProfiles(@PathVariable("username") String username,
                                         @AuthenticationPrincipal AuthUserDetails authUserDetails) {

        return ResponseEntity.ok(profileService.followByUsername(username, authUserDetails));
    }

    @DeleteMapping("/follow")
    public ResponseEntity deleteProfiles(@PathVariable("username") String username,
                                         @AuthenticationPrincipal AuthUserDetails authUserDetails) {

        return ResponseEntity.ok(profileService.unfollowByUsername(username, authUserDetails));
    }
}
