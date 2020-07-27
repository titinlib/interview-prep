package com.supriyanta.interviewprep.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
public class JwtUtil {

    //    @Value("${jwt.secretKey}")
    private static final String secretKey = "verylongsecretkey_verylongsecretkey";

    //    @Value("${jwt.expirationdays}")
    private static final Integer days = 7;

    public static String generateNewJwtToken(String username) {

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setIssuer("me")
                .setSubject(username)
                .setAudience("you")
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(days)))
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(key)
                .compact();
    }

    public static String extractDataFromJwtToken(String token) {

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        Claims body = claims.getBody();

        String username = body.getSubject();

        return username;
    }
}
