package com.example.medicalappointments.rest.dto;

import com.example.medicalappointments.persistence.models.Customer;
import com.example.medicalappointments.rest.dto.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomerDTO extends BaseDTO<Customer> {
    @Getter
    @Setter
    @NotBlank(message = "name.missing")
    @Size(min = 1, max = 50)
    private String name;

    @Getter
    @Setter
    @NotBlank(message = "register.id.missing")
    @Size(min = 1, max = 10)
    private String registerId;

    public CustomerDTO(){super();}

    public CustomerDTO(Customer entity, boolean notDetailed){super(entity);}
}
