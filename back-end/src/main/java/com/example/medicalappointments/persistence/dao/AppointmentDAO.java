package com.example.medicalappointments.persistence.dao;

import com.example.medicalappointments.persistence.dao.base.BaseDAO;
import com.example.medicalappointments.persistence.models.Appointment;
import com.example.medicalappointments.persistence.repository.IAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentDAO extends BaseDAO<Appointment, Long, IAppointment> {

    @Autowired
    protected AppointmentDAO(IAppointment iAppointment){super(iAppointment);}
}
