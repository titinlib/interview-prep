package com.supriyanta.interviewprep.controller;

import com.supriyanta.interviewprep.dto.UserDto;
import com.supriyanta.interviewprep.dto.UserResponseDto;
import com.supriyanta.interviewprep.persisteance.model.AccountUser;
import com.supriyanta.interviewprep.service.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountUserController {

    @Autowired
    private AccountUserService accountUserService;

    @GetMapping("/hello")
    public String testing() {
        return "Tested!";
    }

    @PostMapping("/signup")
    public UserResponseDto registerUser(@Valid @RequestBody UserDto user) throws Exception {
        try {
            AccountUser newUser = accountUserService.registerUser(user);
            return new UserResponseDto(newUser);
        } catch (Exception exception) {
            throw new Exception();
        }
    }

    @PostMapping("/signin")
    public boolean signInUser(@RequestBody UserDto user) {
        return false;
    }
}
