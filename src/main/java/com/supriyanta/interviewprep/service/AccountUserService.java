package com.supriyanta.interviewprep.service;

import com.supriyanta.interviewprep.dto.UserDto;
import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AccountUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountUserRepository accountUserRepository;

    public AccountUser registerUser(UserDto user) throws Exception {
        if(userExists(user)) {
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
}
