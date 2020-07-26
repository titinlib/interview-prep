package com.supriyanta.interviewprep.persisteance.repository;

import com.supriyanta.interviewprep.persisteance.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {
    Optional<AccountUser> findByEmail(String email);
}
