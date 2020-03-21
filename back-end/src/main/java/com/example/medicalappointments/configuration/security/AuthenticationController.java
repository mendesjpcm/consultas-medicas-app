package com.example.medicalappointments.configuration.security;

import com.example.medicalappointments.configuration.security.auth.AuthenticationFilter;
import com.example.medicalappointments.configuration.security.auth.AuthenticationManagerIMPL;
import com.example.medicalappointments.configuration.security.auth.jwt.JWTTokenHelper;
import com.example.medicalappointments.exceptions.ApiError;
import com.example.medicalappointments.persistence.models.ApplicationUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "Authentication", description = "REST Controller for Authentication", tags = "AUTHENTICATION")
public class AuthenticationController {
    private	final JWTTokenHelper helper;
    private	final AuthenticationManagerIMPL manager;
    private	final	AuthenticationBO service;

    @Autowired
    public AuthenticationController(JWTTokenHelper helper, AuthenticationManagerIMPL manager, AuthenticationBO service) {
        this.manager	= manager;
        this.service	= service;
        this.helper		= helper;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO request) throws AuthenticationException {
        try {
            Authentication authentication	= manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), ""));
            HttpHeaders headers			= new HttpHeaders();
            ApplicationUser account = (ApplicationUser) authentication.getPrincipal();

            String token = helper.generateToken(account);

            AuthenticationFilter.saltCache.put(helper.getSessionID(token), "");

            SecurityContextHolder.getContext().setAuthentication(authentication);
            headers.add(HttpHeaders.AUTHORIZATION, token);

            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (Exception e) {
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getCause().getLocalizedMessage());
            return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
        }
    }
}
