package com.example.realworld.service;

import com.example.realworld.dto.UserDto;
import com.example.realworld.entity.UserEntity;
import com.example.realworld.model.LoginParam;
import com.example.realworld.model.RegistrationParam;
import com.example.realworld.repository.UserRepository;
import com.example.realworld.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public UserDto registration(RegistrationParam param) {
        userRepository.findByNameOrEmail(param.getUsername(), param.getEmail())
                .ifPresent((entity) -> {
                    throw new IllegalStateException("用户已存在");
                });
        UserEntity userEntity = UserEntity.builder()
                .name(param.getUsername())
                .email(param.getEmail())
                .bio("")
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

    @Override
    public UserDto currentUser(UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("用户不存在"));
        return convertToUserDto(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto userDto, UserDetails userDetails) {
        UserEntity userEntity = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("用户不存在"));
        if (userDto.getEmail() != null) {
            userRepository.findByEmail(userDto.getEmail())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {
                        throw new IllegalStateException("用户已存在");
                    });
            userEntity.setEmail(userDto.getEmail());
        }
        if (userDto.getUsername() != null) {
            userRepository.findByName(userDto.getUsername())
                    .filter(found -> !found.getId().equals(userEntity.getId()))
                    .ifPresent(found -> {
                        throw new IllegalStateException("用户已存在");
                    });
            userEntity.setName(userDto.getUsername());
        }
        if (userDto.getBio() != null) {
            userEntity.setBio(userDto.getBio());
        }
        if (userDto.getImage() != null) {
            userEntity.setImage(userDto.getImage());
        }
        userRepository.save(userEntity);
        return convertToUserDto(userEntity);
    }

    private UserDto convertToUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .username(userEntity.getName())
                .email(userEntity.getEmail())
                .bio("")
                .image("")
                .token(jwtUtils.encode(userEntity.getEmail()))
                .build();
    }
}
