package com.supriyanta.interviewprep.persisteance.repository;

import com.supriyanta.interviewprep.persisteance.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
