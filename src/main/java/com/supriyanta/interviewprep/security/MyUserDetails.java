package com.supriyanta.interviewprep.security;

import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.model.Authority;
import com.supriyanta.interviewprep.persistence.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
public class MyUserDetails extends AccountUser implements UserDetails {

    private final AccountUser user;

    public MyUserDetails(AccountUser user) {
        super(user);

        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            return getAuthoritiesFromRoles(user.getRoles());
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromRoles(List<Role> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

            for (Authority authority : role.getAuthorities()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }

        System.out.println(grantedAuthorities);
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
        return true;
    }
}
