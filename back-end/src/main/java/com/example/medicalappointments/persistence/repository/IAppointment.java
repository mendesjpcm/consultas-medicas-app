package com.example.medicalappointments.persistence.repository;

import com.example.medicalappointments.persistence.models.Appointment;
import com.example.medicalappointments.persistence.repository.base.IBase;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppointment extends IBase<Appointment, Long> {
}
