package com.supriyanta.interviewprep.util;

import com.supriyanta.interviewprep.constants.JwtConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private JwtConstants jwtConstants;

    public String generateNewJwtToken(String username) {

        SecretKey key = Keys.hmacShaKeyFor(jwtConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setIssuer("me") // TODO: change
                .setSubject(username)
                .setAudience("you")   // TODO: change
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConstants.JWT_EXPIRATIONDAYS)))
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(key)
                .compact();
    }

    public String extractDataFromJwtToken(String token) {

        SecretKey key = Keys.hmacShaKeyFor(jwtConstants.JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        Claims body = claims.getBody();

        String username = body.getSubject();

        return username;
    }
}
