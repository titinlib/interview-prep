package com.supriyanta.interviewprep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/public")
    public String hello() {
        return "Hello from the sky";
    }

    @GetMapping("/test")
    public String authTest() {
        return "Auth testing from the sky";
    }
}
