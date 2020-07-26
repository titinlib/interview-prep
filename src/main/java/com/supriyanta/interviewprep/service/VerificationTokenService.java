package com.supriyanta.interviewprep.service;

import com.supriyanta.interviewprep.persistence.model.AccountUser;
import com.supriyanta.interviewprep.persistence.model.VerificationToken;
import com.supriyanta.interviewprep.persistence.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createTokenWithUser(AccountUser user) {
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken(token, user);

        return verificationTokenRepository.save(verificationToken);
    }
}
