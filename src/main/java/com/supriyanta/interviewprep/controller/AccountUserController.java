package com.supriyanta.interviewprep.controller;

import com.supriyanta.interviewprep.dto.JwtDto;
import com.supriyanta.interviewprep.dto.UserDto;
import com.supriyanta.interviewprep.dto.UserResponseDto;
import com.supriyanta.interviewprep.event.UserRegistrationEvent;
import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.security.UsernameAndPasswordRequest;
import com.supriyanta.interviewprep.service.AccountUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AccountUserController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private AccountUserService accountUserService;

    @GetMapping("/hello")
    public String testing() {
        return "Tested!";
    }

    @PostMapping("/signup")
    public UserResponseDto registerUser(@Valid @RequestBody UserDto user) throws Exception {

        log.info("POST /auth/signup");

        try {
            AccountUser newUser = accountUserService.registerUser(user);

            // Publishing User Registration Event
            applicationEventPublisher.publishEvent(new UserRegistrationEvent(this, newUser));

            return new UserResponseDto(newUser);

        } catch (Exception exception) {
            throw new Exception();
        }
    }

    @PostMapping("/signin")
    public JwtDto authenticateUser(@Valid @RequestBody UsernameAndPasswordRequest authRequest) {

        log.info("POST /auth/signin");

        String jwtToken = accountUserService.authenticateUser(authRequest);

        return new JwtDto(jwtToken);
    }
}
