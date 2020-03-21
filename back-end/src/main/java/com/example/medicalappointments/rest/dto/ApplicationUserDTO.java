package com.example.medicalappointments.rest.dto;

import com.example.medicalappointments.persistence.models.ApplicationUser;
import com.example.medicalappointments.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ApplicationUserDTO extends BaseDTO<ApplicationUser> {
    @Getter
    @Setter
    @NotBlank(message = "name.missing")
    @Size(min = 1, max = 50)
    private String name;

    @Getter
    @Setter
    @NotBlank(message = "email.missing")
    @Size(min = 1, max = 50)
    @Email(message = "email.is.out.of.pattern")
    private String email;

    public ApplicationUserDTO() {
        super();
    }

    public ApplicationUserDTO(ApplicationUser entity, boolean notDetailed) {
        super(entity);
    }
}
