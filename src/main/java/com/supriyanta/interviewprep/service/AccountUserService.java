package com.supriyanta.interviewprep.service;

import com.supriyanta.interviewprep.dto.UserDto;
import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.repository.AccountUserRepository;
import com.supriyanta.interviewprep.security.UsernameAndPasswordRequest;
import com.supriyanta.interviewprep.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class AccountUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountUserRepository accountUserRepository;

    public AccountUser registerUser(UserDto user) throws Exception {
        if (userExists(user)) {
            throw new Exception("User with this email already exists!");
        }
        AccountUser saveUser = new AccountUser();
        saveUser.setEmail(user.getEmail());
        saveUser.setName(user.getName());
        // TODO: encode password
        saveUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return accountUserRepository.save(saveUser);
    }

    private boolean userExists(UserDto user) {
        Optional<AccountUser> userExist = accountUserRepository.findByEmail(user.getEmail());

        return userExist.isPresent();
    }

    public String authenticateUser(UsernameAndPasswordRequest authRequest) {
        authenticateRequest(authRequest);
        String jwtToken = JwtUtil.generateNewJwtToken(authRequest.getEmail());
        return jwtToken;
    }

    private void authenticateRequest(UsernameAndPasswordRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()
            ));

        } catch (Exception exception) {
            log.warn("exception in user controller", exception);
        }
    }
}
