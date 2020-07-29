package com.supriyanta.interviewprep.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(SimpleMailMessage email) {
        try {
            mailSender.send(email);
        } catch (Exception exception) {
            log.warn(exception.toString());
            // TODO: Create new Exception class
            throw new IllegalStateException("Error sending mail!");
        }
    }
}
