package com.example.medicalappointments.persistence.repository;

import com.example.medicalappointments.persistence.models.Doctor;
import com.example.medicalappointments.persistence.repository.base.IBase;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDoctor extends IBase<Doctor, Long> {

    Optional<Doctor> findByCrm(String crm);
}
