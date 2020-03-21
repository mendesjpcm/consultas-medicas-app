package com.example.medicalappointments.rest.controller;

import com.example.medicalappointments.rest.controller.base.BaseController;
import com.example.medicalappointments.rest.dto.DoctorDTO;
import com.example.medicalappointments.rest.service.DoctorBO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Doctor", description = "REST Controller for Doctor", tags = "DOCTOR")
@RequestMapping(value = "/rest/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class DoctorController extends BaseController<DoctorBO, DoctorDTO> {
    @Autowired
    public DoctorController(DoctorBO bo){super(bo);}
}
