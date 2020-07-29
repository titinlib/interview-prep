package com.supriyanta.interviewprep.service;

import com.supriyanta.interviewprep.dto.UserDto;
import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.model.VerificationToken;
import com.supriyanta.interviewprep.persistence.repository.AccountUserRepository;
import com.supriyanta.interviewprep.persistence.repository.VerificationTokenRepository;
import com.supriyanta.interviewprep.security.UsernameAndPasswordRequest;
import com.supriyanta.interviewprep.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    private JwtUtil jwtUtil;

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public AccountUser registerUser(UserDto user) throws Exception {

        if (userExists(user)) {
            log.info(user.toString());
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
        String jwtToken = jwtUtil.generateNewJwtToken(authRequest.getEmail());
        return jwtToken;
    }

    private void authenticateRequest(UsernameAndPasswordRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        ));
        log.info(authentication.toString());

        if (authentication.isAuthenticated() != true) {
            throw new BadCredentialsException("Bad Credentials");
        }
    }

    public Optional<AccountUser> findUserByEmail(String email) {
        return accountUserRepository.findByEmail(email);
    }

    // TODO: use PreAuthorize before accessing this method
    public List<AccountUser> findAllUser() {
        return accountUserRepository.findAll();
    }

    public void confirmRegistration(String token) {
        Optional<VerificationToken> tokenExists = verificationTokenRepository.findByToken(token);

        VerificationToken verificationToken = tokenExists
                .orElseThrow(() -> new IllegalStateException("Token not found!"));

        AccountUser user = verificationToken.getUser();

        user.setEnabled(true);

        accountUserRepository.save(user);

        verificationTokenRepository.deleteById(verificationToken.getId());
    }

    public void deleteUser(AccountUser user) {
        accountUserRepository.deleteById(user.getId());
    }
}
