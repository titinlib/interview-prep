package com.supriyanta.interviewprep.security;

import com.supriyanta.interviewprep.constants.JwtConstants;
import com.supriyanta.interviewprep.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@Component
@Slf4j
public class JWTTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConstants jwtConstants;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = httpServletRequest.getHeader(jwtConstants.JWT_AUTHORIZATION_HEADER_STRING);

        String username = null;

        if (bearerToken != null && bearerToken.startsWith(jwtConstants.JWT_TOKEN_PREFIX)) {
            String token = bearerToken.replace(jwtConstants.JWT_TOKEN_PREFIX, "");

            try {
                username = jwtUtil.extractDataFromJwtToken(token);
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
