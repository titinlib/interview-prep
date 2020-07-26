package com.supriyanta.interviewprep.security;

import com.supriyanta.interviewprep.persisteance.model.AccountUser;
import com.supriyanta.interviewprep.persisteance.model.Authority;
import com.supriyanta.interviewprep.persisteance.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetails extends AccountUser implements UserDetails {

    private AccountUser user;

    public MyUserDetails(AccountUser user) {
        super(user);

        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesFromRoles(user.getRoles());
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromRoles(Set<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Role role: roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

            for(Authority authority : role.getAuthorities()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
