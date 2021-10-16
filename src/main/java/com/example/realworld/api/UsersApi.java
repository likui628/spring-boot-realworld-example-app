package com.example.realworld.api;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.example.realworld.api.exception.InvalidRequestException;
import com.example.realworld.model.User;
import com.example.realworld.service.UsersService;
import com.fasterxml.jackson.annotation.JsonRootName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(path = "users")
public class UsersApi {
    private final UsersService usersService;

    @Autowired
    public UsersApi(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> creeteUser(@Valid @RequestBody RegisterParam registerParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
        if (usersService.findByUsername(registerParam.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "duplicated", "username duplicated");
            throw new InvalidRequestException(bindingResult);
        }
        if (usersService.findByEmail(registerParam.getEmail()).isPresent()) {
            bindingResult.rejectValue("username", "duplicated", "email duplicated");
            throw new InvalidRequestException(bindingResult);
        }
        User user = new User(registerParam.getEmail(), registerParam.getUsername(), registerParam.getPassword(), "",
                "");
        usersService.save(user);
        return ResponseEntity.status(201).body(usersService.findByUsername(user.getUsername()));
    }
}

@Getter
@JsonRootName("user")
@NoArgsConstructor
class RegisterParam {
    @NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    private String email;

    @NotBlank(message = "can't be empty")
    private String username;

    @NotBlank(message = "can't be empty")
    private String password;
}
