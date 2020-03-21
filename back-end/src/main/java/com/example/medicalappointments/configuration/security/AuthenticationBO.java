package com.example.medicalappointments.configuration.security;

import com.example.medicalappointments.exceptions.NotFoundException;
import com.example.medicalappointments.persistence.dao.ApplicationUserDAO;
import com.example.medicalappointments.persistence.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationBO implements UserDetailsService {
    private ApplicationUserDAO userDAO;
    private PasswordEncoder encoder;

    @Autowired
    public AuthenticationBO(ApplicationUserDAO dao, @Lazy PasswordEncoder passwordEncoder){
        this.userDAO = dao;
        this.encoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        ApplicationUser user = userDAO.findByUsername(username);

        return user;
    }
}
