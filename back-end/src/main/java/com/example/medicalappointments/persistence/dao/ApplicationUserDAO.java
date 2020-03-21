package com.example.medicalappointments.persistence.dao;

import com.example.medicalappointments.persistence.dao.base.BaseDAO;
import com.example.medicalappointments.persistence.models.ApplicationUser;
import com.example.medicalappointments.persistence.repository.IApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDAO extends BaseDAO<ApplicationUser, Long, IApplicationUser> {
    @Autowired
    protected ApplicationUserDAO(IApplicationUser iApplicationUser) {
        super(iApplicationUser);
    }

    public ApplicationUser findByEmail(String email){
        return getRepository().findByEmail(email).orElse(null);
    }

    public ApplicationUser findByUsername(String username){
        return getRepository().findByUsername(username);
    }
}
