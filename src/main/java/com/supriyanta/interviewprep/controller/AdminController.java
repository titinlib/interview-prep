package com.supriyanta.interviewprep.controller;

import com.supriyanta.interviewprep.persistence.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return null;
    }
}
