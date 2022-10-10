package com.example.realworld.configuration;

import com.example.realworld.entity.UserEntity;
import com.example.realworld.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserEntity user = UserEntity.builder()
                .username(faker.name().username())
                .email(faker.internet().emailAddress())
                .image(faker.avatar().image())
                .bio(faker.chuckNorris().fact())
                .password(passwordEncoder.encode("123456"))
                .build();

        userRepository.save(user);

    }
}
