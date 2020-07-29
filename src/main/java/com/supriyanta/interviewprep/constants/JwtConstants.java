package com.supriyanta.interviewprep.constants;

import org.springframework.stereotype.Component;

@Component
public class JwtConstants {
    public final String JWT_SECRET = "verylongsecretkeyverylongsecretkey";
    public final Integer JWT_EXPIRATIONDAYS = 7;
    public final String JWT_TOKEN_PREFIX = "Bearer ";
    public final String JWT_AUTHORIZATION_HEADER_STRING = "Authorization";
}
