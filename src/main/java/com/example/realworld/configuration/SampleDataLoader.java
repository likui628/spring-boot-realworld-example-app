package com.example.realworld.configuration;

import com.example.realworld.repository.UserRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final Faker faker;


    @Override
    public void run(String... args) throws Exception {

    }
}
