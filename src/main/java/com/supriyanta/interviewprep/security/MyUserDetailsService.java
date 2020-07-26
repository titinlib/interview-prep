package com.supriyanta.interviewprep.security;

import com.supriyanta.interviewprep.persisteance.model.AccountUser;
import com.supriyanta.interviewprep.persisteance.repository.AccountUserRepository;
import com.supriyanta.interviewprep.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

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