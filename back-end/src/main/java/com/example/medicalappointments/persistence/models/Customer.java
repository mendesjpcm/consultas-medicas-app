package com.example.medicalappointments.persistence.models;

import com.example.medicalappointments.persistence.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends BaseEntity {

    @Getter
    @Setter
    @Column(nullable = false, length = 50)
    private String name;

    @Getter
    @Setter
    @Column(name = "register_id", nullable = false, length = 10)
    private String registerId;

    @Getter
    @Setter
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Appointment appointment;

}
