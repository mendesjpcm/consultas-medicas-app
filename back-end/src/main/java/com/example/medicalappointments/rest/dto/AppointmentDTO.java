package com.example.medicalappointments.rest.dto;

import com.example.medicalappointments.persistence.models.Appointment;
import com.example.medicalappointments.rest.dto.base.BaseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class AppointmentDTO extends BaseDTO<Appointment> {
    @Getter
    @Setter
    @NotBlank(message = "local.missing")
    @Size(max = 255)
    private String local;

    @Getter
    @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Getter
    @Setter
    private @Valid DoctorDTO doctor;

    @Getter
    @Setter
    private @Valid CustomerDTO customer;

    public AppointmentDTO(){super();}

    public AppointmentDTO(Appointment entity, boolean notDetailed){super(entity);}
}
