package com.example.medicalappointments.persistence.dao.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface IBaseDAO <T, ID extends Serializable>{

    T findOne(ID id);

    Iterable<T> findAll();

    Page<T> findAll(Pageable pageable);

    T save(T model);

    Iterable<T> save(Iterable<T> models);

    void delete(ID id);

    void delete(T entity);
}
