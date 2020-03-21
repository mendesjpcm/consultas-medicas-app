package com.example.medicalappointments.persistence.models;

import com.example.medicalappointments.persistence.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doctor", uniqueConstraints = {@UniqueConstraint(columnNames = "crm", name = "crm")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Doctor extends BaseEntity {

    @Getter
    @Setter
    @Column(nullable = false, length = 50)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false, length = 8)
    private String crm;

    @Getter
    @Setter
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private Collection<Appointment> appointments;
}
