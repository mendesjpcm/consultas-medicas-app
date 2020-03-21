package com.example.medicalappointments.rest.service;

import com.example.medicalappointments.persistence.dao.AppointmentDAO;
import com.example.medicalappointments.persistence.dao.DoctorDAO;
import com.example.medicalappointments.persistence.models.Appointment;
import com.example.medicalappointments.persistence.models.Doctor;
import com.example.medicalappointments.rest.dto.AppointmentDTO;
import com.example.medicalappointments.rest.service.base.BaseBO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AppointmentBO extends BaseBO<Appointment, AppointmentDAO, AppointmentDTO, Long> {

    @Getter(AccessLevel.PRIVATE)
    @Setter(value = AccessLevel.PRIVATE, onMethod = @__({@Autowired}))
    private DoctorDAO doctorDAO;

    @Autowired
    protected AppointmentBO(AppointmentDAO dao){super(dao);}

    @Override
    public AppointmentDTO save(AppointmentDTO create, UserDetails details){
        Doctor doctor = getDoctorDAO().findOne(create.getDoctor().getId());
        Appointment appointment = create.getModel();
        appointment.setDoctor(doctor);
        return toDTO(getDao().save(appointment), true);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO update, UserDetails details){
        Appointment appointment = getDao().findOne(update.getId());
        Doctor doctor = getDoctorDAO().findOne(update.getDoctor().getId());
        appointment.setLocal(update.getLocal());
        appointment.setDate(update.getDate());
        update.getCustomer().setId(Long.valueOf(0));
        appointment.setCustomer(update.getCustomer().getModel());
        appointment.setDoctor(doctor);
        return toDTO(getDao().save(appointment), true);
    }
}
