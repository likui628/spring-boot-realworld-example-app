package com.example.realworld.service;

import com.example.realworld.dto.UserDto;
import com.example.realworld.entity.UserEntity;
import com.example.realworld.model.LoginParam;
import com.example.realworld.model.RegistrationParam;
import com.example.realworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registration(RegistrationParam param) {
        userRepository.findByUsernameOrEmail(param.getUsername(), param.getEmail())
                .ifPresent((entity) -> {
                    System.out.println(entity);
                    throw new IllegalStateException("用户已存在");
                });
        UserEntity userEntity = UserEntity.builder()
                .username(param.getUsername())
                .email(param.getEmail()).bio("")
                .password(passwordEncoder.encode(param.getPassword()))
                .build();
        userRepository.save(userEntity);
        return convertToUserDto(userEntity);
    }

    @Override
    public UserDto login(LoginParam param) {
        UserEntity userEntity = userRepository.findByEmail(param.getEmail())
                .filter(user -> passwordEncoder.matches(param.getPassword(), user.getPassword()))
                .orElseThrow(() -> new IllegalStateException("用户不存在"));
        return convertToUserDto(userEntity);
    }

    private UserDto convertToUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .bio("")
                .image("")
                .token("fakeToken")
                .build();
    }
}
