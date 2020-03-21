package com.example.medicalappointments.rest.controller;

import com.example.medicalappointments.rest.controller.base.BaseController;
import com.example.medicalappointments.rest.dto.AppointmentDTO;
import com.example.medicalappointments.rest.service.AppointmentBO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Medical Appointment", description = "REST Controller for Medical Appointment", tags = "MEDICAL APPOINTMENT")
@RequestMapping(value = "/rest/appointment", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AppointmentController extends BaseController<AppointmentBO, AppointmentDTO> {
    @Autowired
    protected AppointmentController(AppointmentBO bo){super(bo);}
}
