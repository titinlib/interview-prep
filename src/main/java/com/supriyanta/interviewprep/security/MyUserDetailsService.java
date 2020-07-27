package com.supriyanta.interviewprep.security;

import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AccountUser> userFound = accountUserRepository.findByEmail(email);

        AccountUser user = userFound.orElseThrow(() ->
                new UsernameNotFoundException("User with the email " + email + " not found"));

        return new MyUserDetails(user);
    }
}
