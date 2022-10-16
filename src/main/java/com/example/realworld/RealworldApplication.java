package com.example.realworld;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealworldApplication.class, args);
    }

    @Bean
    public Faker getFaker(){
        return new Faker();
    }

}
