package com.supriyanta.interviewprep.event.eventListener;

import com.supriyanta.interviewprep.event.UserRegistrationEvent;
import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.model.VerificationToken;
import com.supriyanta.interviewprep.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationEventListener {

    @Autowired
    private VerificationTokenService verificationTokenService;

    // TODO: try using this in load testing
    @Async
    @EventListener({UserRegistrationEvent.class})
    public void handleUserRegistration(UserRegistrationEvent event) {
        System.out.println("..............................................");
        System.out.println("UserRegistrationEvent Handled!");

        AccountUser user = event.getUser();

        VerificationToken token = verificationTokenService.createTokenWithUser(user);

        // TODO: send mail
        // TODO: send warning please confirm the email within 24 hours
    }
}
