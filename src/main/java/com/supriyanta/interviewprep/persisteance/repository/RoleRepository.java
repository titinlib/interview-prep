package com.supriyanta.interviewprep.persisteance.repository;

import com.supriyanta.interviewprep.persisteance.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
