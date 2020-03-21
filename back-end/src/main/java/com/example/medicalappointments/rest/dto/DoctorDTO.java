package com.example.medicalappointments.rest.dto;

import com.example.medicalappointments.persistence.models.Doctor;
import com.example.medicalappointments.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DoctorDTO extends BaseDTO<Doctor> {
    @Getter
    @Setter
    @NotBlank(message = "name.missing")
    @Size(min = 1, max = 50)
    private String name;

    @Getter
    @Setter
    @NotBlank(message = "crm.missing")
    @Size(min = 1, max = 8)
    private String crm;

    public DoctorDTO(){super();}

    public DoctorDTO(Doctor entity, boolean notDetailed){super(entity);}
}
