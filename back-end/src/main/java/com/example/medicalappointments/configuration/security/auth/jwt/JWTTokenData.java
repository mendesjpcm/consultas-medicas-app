package com.example.medicalappointments.configuration.security.auth.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class JWTTokenData {
    @Getter
    @Setter
    @JsonProperty(value = "user_id")
    private Long userID;

    @Getter
    @Setter
    @JsonProperty(value = "sid")
    private String sessionID;

    @Getter
    @Setter
    @JsonProperty(value = "name")
    private String name;

    @Getter
    @Setter
    @JsonProperty(value = "username")
    private String username;

    @Getter
    @Setter
    @JsonProperty(value = "roles")
    private String roles;
}
