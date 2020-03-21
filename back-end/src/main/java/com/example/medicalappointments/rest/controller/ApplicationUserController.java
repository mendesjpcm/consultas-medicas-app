package com.example.medicalappointments.rest.controller;

import com.example.medicalappointments.rest.dto.ApplicationUserDTO;
import com.example.medicalappointments.rest.service.ApplicationUserBO;
import io.swagger.annotations.Api;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(value = "Application User", description = "REST Controller for Application User", tags = "APPLICATION USER")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ApplicationUserController {

    @Getter(AccessLevel.PROTECTED)
    private final ApplicationUserBO applicationUserBO;

    @Autowired
    public ApplicationUserController(ApplicationUserBO bo){ applicationUserBO = bo;}

    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public ResponseEntity<?> save(@RequestBody @Valid ApplicationUserDTO dto, @ApiIgnore @AuthenticationPrincipal UserDetails details){
        return ResponseEntity.ok(getApplicationUserBO().save(dto, details));
    }
}
