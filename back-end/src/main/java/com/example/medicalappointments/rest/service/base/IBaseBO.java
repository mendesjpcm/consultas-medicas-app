package com.example.medicalappointments.rest.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public interface IBaseBO <TEntity, TDTO, TPK extends Serializable> {

    TDTO findOne(TPK id, UserDetails details);

    Collection<TDTO> findAll (UserDetails details);

    Page<TDTO> findAll(Pageable pageable, UserDetails details);

    void delete (TEntity entity);

    void delete(TPK id);

    TDTO save(TDTO create, UserDetails details);

    TDTO update(TDTO update, UserDetails details);
}
