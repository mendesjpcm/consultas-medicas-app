package com.example.medicalappointments.rest.service;

import com.example.medicalappointments.exceptions.ConflictException;
import com.example.medicalappointments.persistence.dao.ApplicationUserDAO;
import com.example.medicalappointments.persistence.models.ApplicationUser;
import com.example.medicalappointments.rest.dto.ApplicationUserDTO;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserBO {
    @Getter(AccessLevel.PRIVATE)
    private ApplicationUserDAO applicationUserDAO;

    @Autowired
    public ApplicationUserBO(ApplicationUserDAO dao){ applicationUserDAO = dao;}

    public ApplicationUserDTO save(ApplicationUserDTO create, UserDetails details){
        ApplicationUser applicationUser = getApplicationUserDAO().findByEmail(create.getEmail());

        if(applicationUser != null){
            throw new ConflictException("email.already.exists", new Throwable("email.already.exists"));
        }

        create.setId(Long.valueOf(0));
        applicationUser = create.getModel();
        applicationUser.setUsername(create.getEmail());
        applicationUser.setPassword(create.getEmail());

        return new ApplicationUserDTO(getApplicationUserDAO().save(applicationUser), true);
    }
}
