package com.supriyanta.interviewprep.persisteance.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class AccountUser extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 50)
    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @Size(min = 6, max = 63)
    @NotNull
    private String password;

    private boolean enabled;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public AccountUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public AccountUser() {
        this.enabled = false;
    }

    public AccountUser(AccountUser user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();

        this.roles = user.getRoles();
    }
}