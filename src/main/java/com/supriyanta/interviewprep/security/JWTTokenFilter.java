package com.supriyanta.interviewprep.security;

import com.supriyanta.interviewprep.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    //    @Value("${jwt.authorizationHeaderString}")
    private final String authorizationHeaderString = "Authorization";

    //    @Value("${jwt.tokenPrefix}")
    private final String tokenPrefix = "Bearer ";

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = httpServletRequest.getHeader(authorizationHeaderString);

        String username = null;

        if (bearerToken != null && bearerToken.startsWith(tokenPrefix)) {
            String token = bearerToken.replace(tokenPrefix, "");

            try {
                username = JwtUtil.extractDataFromJwtToken(token);
            } catch (Exception e) {
                log.warn("JWT exception!", e);
            }
        } else {
            log.info("JWT not found or invalid!");
        }

        if (username != null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            var authorities = (List<GrantedAuthority>) userDetails.getAuthorities();

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
