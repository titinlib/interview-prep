package com.supriyanta.interviewprep.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsernameAndPasswordRequest {

    private String email;

    private String password;

    public UsernameAndPasswordRequest() {
    }

    public UsernameAndPasswordRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }
}
