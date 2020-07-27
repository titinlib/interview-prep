package com.supriyanta.interviewprep.event;

import com.supriyanta.interviewprep.persistence.model.AccountUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserRegistrationEvent extends ApplicationEvent {

    private AccountUser user;

    public UserRegistrationEvent(Object source, AccountUser user) {
        super(source);
        this.user = user;
    }
}
