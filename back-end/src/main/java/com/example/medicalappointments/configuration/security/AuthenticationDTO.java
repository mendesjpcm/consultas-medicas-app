package com.example.medicalappointments.configuration.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationDTO {
    @Getter
    @Setter
    @NotBlank(message = "username.missing")
    private		String		username;

}
