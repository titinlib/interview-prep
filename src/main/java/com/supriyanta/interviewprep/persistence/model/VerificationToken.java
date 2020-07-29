package com.supriyanta.interviewprep.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "verificaton_tokens")
@Getter
@Setter
public class VerificationToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String token;

    @OneToOne(targetEntity = AccountUser.class,
            fetch = FetchType.EAGER
            )
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AccountUser user;

    public VerificationToken(String token, AccountUser user) {
        this.token = token;
        this.user = user;
    }

    public VerificationToken() {
    }
}
