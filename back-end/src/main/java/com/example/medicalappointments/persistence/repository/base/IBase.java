package com.example.medicalappointments.persistence.repository.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

import java.io.Serializable;

@NoRepositoryBean
@RestResource(exported = false)
public interface IBase <T, ID extends Serializable> extends JpaRepository<T,ID> {
}
