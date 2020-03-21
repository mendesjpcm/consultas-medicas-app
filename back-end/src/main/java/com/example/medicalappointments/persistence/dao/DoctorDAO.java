package com.example.medicalappointments.persistence.dao;

import com.example.medicalappointments.persistence.dao.base.BaseDAO;
import com.example.medicalappointments.persistence.models.Doctor;
import com.example.medicalappointments.persistence.repository.IDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorDAO extends BaseDAO<Doctor, Long, IDoctor> {

    @Autowired
    protected DoctorDAO(IDoctor iDoctor){super(iDoctor);}

    public Doctor findByCrm(String crm){
        return getRepository().findByCrm(crm).orElse(null);
    }
}
