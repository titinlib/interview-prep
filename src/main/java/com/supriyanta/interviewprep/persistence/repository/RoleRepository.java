package com.supriyanta.interviewprep.persistence.repository;

import com.supriyanta.interviewprep.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
