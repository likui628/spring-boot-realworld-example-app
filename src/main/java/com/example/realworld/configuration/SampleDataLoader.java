        package com.example.realworld.configuration;

        import com.example.realworld.entity.UserEntity;
        import com.example.realworld.repository.UserRepository;
        import com.github.javafaker.Faker;
        import lombok.RequiredArgsConstructor;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final Faker faker;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        UserEntity user = UserEntity.builder()
                .name(faker.name().username())
                .email("abc@efg.hij")
                .image(faker.avatar().image())
                .bio(faker.chuckNorris().fact())
                .password(passwordEncoder.encode("123456"))
                .build();

        userRepository.save(user);

    }
}