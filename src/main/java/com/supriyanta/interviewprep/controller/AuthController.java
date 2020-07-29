package com.supriyanta.interviewprep.controller;

import com.supriyanta.interviewprep.dto.JwtDto;
import com.supriyanta.interviewprep.dto.ResponseDto;
import com.supriyanta.interviewprep.dto.UserDto;
import com.supriyanta.interviewprep.dto.UserResponseDto;
import com.supriyanta.interviewprep.event.UserRegistrationEvent;
import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.security.UsernameAndPasswordRequest;
import com.supriyanta.interviewprep.service.AccountUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private AccountUserService accountUserService;

    // TODO: Delete after testing
    @GetMapping("/hello")
    public ResponseDto<List<AccountUser>> getAllUser() {
        List<AccountUser> users = accountUserService.findAllUser();
        return new ResponseDto<>(users, HttpStatus.ACCEPTED);
    }

    // TODO: Delete after tesing
    @GetMapping("/hello/{email}")
    public ResponseDto<AccountUser> getUser(@PathVariable String email) throws Exception {
        AccountUser user = accountUserService.findUserByEmail(email)
                                    .orElseThrow(Exception::new);
        return new ResponseDto<>(user);
    }

    @PostMapping("/signup")
    public ResponseDto<UserResponseDto> registerUser(@Valid @RequestBody UserDto user) throws Exception {

        log.info("POST /auth/signup");

        try {
            AccountUser newUser = accountUserService.registerUser(user);

            // Publishing User Registration Event
            applicationEventPublisher.publishEvent(new UserRegistrationEvent(this, newUser));

            return new ResponseDto<>(new UserResponseDto(newUser), HttpStatus.CREATED);

        } catch (Exception exception) {
            throw new Exception();
        }
    }

    @PostMapping("/signin")
    public ResponseDto<JwtDto> authenticateUser(@Valid @RequestBody UsernameAndPasswordRequest authRequest) {

        log.info("POST /auth/signin");

        String jwtToken = accountUserService.authenticateUser(authRequest);

        return new ResponseDto<>(new JwtDto(jwtToken), HttpStatus.ACCEPTED);
    }
}
