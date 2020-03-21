package com.example.medicalappointments.rest.service;

import com.example.medicalappointments.exceptions.ConflictException;
import com.example.medicalappointments.persistence.dao.DoctorDAO;
import com.example.medicalappointments.persistence.models.Doctor;
import com.example.medicalappointments.rest.dto.DoctorDTO;
import com.example.medicalappointments.rest.service.base.BaseBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class DoctorBO extends BaseBO<Doctor, DoctorDAO, DoctorDTO, Long> {

    @Autowired
    protected DoctorBO(DoctorDAO dao){super(dao);}

    @Override
    public DoctorDTO save(DoctorDTO create, UserDetails details){
        Doctor doctor = getDao().findByCrm(create.getCrm());
        if(doctor != null){
            throw new ConflictException("crm.already.exists", new Throwable("crm.already.exists"));
        }

        create.setId(Long.valueOf(0));

        return toDTO(getDao().save(create.getModel()), true);
    }

    public DoctorDTO update(DoctorDTO update, UserDetails details){
        Doctor doctor = getDao().findOne(update.getId());
        Doctor check_doctor = getDao().findByCrm(update.getCrm());
        if(check_doctor!= null && check_doctor.getId() != doctor.getId()){
            throw new ConflictException("crm.already.exists", new Throwable("crm.already.exists"));
        }
        doctor.setName(update.getName());
        doctor.setCrm(update.getCrm());
        return toDTO(getDao().save(doctor), true);
    }
}
