package com.example.realworld.service;

import com.example.realworld.dto.UserDto;
import com.example.realworld.entity.UserEntity;
import com.example.realworld.model.LoginParam;
import com.example.realworld.model.RegistrationParam;
import com.example.realworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private  final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registration(RegistrationParam param) {
        //todo check if user existed
        UserEntity userEntity = UserEntity.builder()
                .username(param.getUsername())
                .email(param.getEmail()).bio("")
                .password(passwordEncoder.encode(param.getPassword()))
                .build();
        userRepository.save(userEntity);
        //toto convert userEntity to UserDto
        return null;
    }

    @Override
    public UserDto login(LoginParam param) {
        System.out.println(param);
        Optional<UserEntity> userEntity = userRepository.findByEmail(param.getEmail())
                .filter(user -> passwordEncoder.matches(param.getPassword(), user.getPassword()));
        return null;
    }
}
