package com.supriyanta.interviewprep.event.eventListener;

import com.supriyanta.interviewprep.constants.GlobalConstants;
import com.supriyanta.interviewprep.errors.InternalSystemError;
import com.supriyanta.interviewprep.event.UserRegistrationEvent;
import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.model.VerificationToken;
import com.supriyanta.interviewprep.service.AccountUserService;
import com.supriyanta.interviewprep.service.EmailService;
import com.supriyanta.interviewprep.service.VerificationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRegistrationEventListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private GlobalConstants globalConstants;

    // TODO: try using this in load testing
    @Async
    @EventListener({UserRegistrationEvent.class})
    public void handleUserRegistration(UserRegistrationEvent event) {
        System.out.println("..............................................");
        System.out.println("UserRegistrationEvent Handled!");

        AccountUser user = event.getUser();

        VerificationToken token = verificationTokenService.createTokenWithUser(user);

        String recipient = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = globalConstants.APP_URL + "/confirm?token=" + token;
        String message = String
                .format("Welcome %s.\nPlease click the link below to activate your account!\n", user.getName());

        String text = String.format("%s %s", message, confirmationUrl);

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(recipient);
        email.setSubject(subject);
        email.setText(text);

//        try {
//            emailService.sendSimpleMail(email);
//        } catch (Exception exception) {
//
//            // delete user
//            accountUserService.deleteUser(user);
//
//            log.warn(exception.toString());
//
//            throw new InternalSystemError();
//        }
    }
}
