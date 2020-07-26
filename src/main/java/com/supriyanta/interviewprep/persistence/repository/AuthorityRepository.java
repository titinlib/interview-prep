package com.supriyanta.interviewprep.persistence.repository;

import com.supriyanta.interviewprep.persistence.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
