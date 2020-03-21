package com.example.medicalappointments.persistence.repository;

import com.example.medicalappointments.persistence.models.ApplicationUser;
import com.example.medicalappointments.persistence.repository.base.IBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IApplicationUser extends IBase<ApplicationUser, Long> {

    Optional<ApplicationUser> findByEmail(String email);

    ApplicationUser findByUsername(String username);
}
