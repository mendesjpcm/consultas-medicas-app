package com.example.medicalappointments.rest.service.base;

import com.example.medicalappointments.exceptions.BadRequestException;
import com.example.medicalappointments.exceptions.NotFoundException;
import com.example.medicalappointments.persistence.dao.base.IBaseDAO;
import com.example.medicalappointments.rest.dto.base.IBaseDTO;
import lombok.AccessLevel;
import lombok.Getter;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class BaseBO <TEntity, TDAO extends IBaseDAO<TEntity, TPK>, TDTO extends IBaseDTO<TEntity>, TPK extends Serializable> implements IBaseBO<TEntity, TDTO, TPK>{
    @Getter(AccessLevel.PROTECTED)
    private final TDAO dao;
    private final Class<TEntity> entity;
    private final Class<TDTO> dto;

    @SuppressWarnings("unchecked")
    @Inject
    protected BaseBO(TDAO dao) {
        entity = (Class<TEntity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        dto = (Class<TDTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        this.dao = dao;
    }

    @Override
    public TDTO findOne(TPK id, UserDetails details) {
        return toDTO(getDao().findOne(id), true);
    }

    @Override
    public Collection<TDTO> findAll(UserDetails details) {
        return StreamSupport.stream(getDao().findAll().spliterator(), true).map(item -> toDTO(item, false)).collect(Collectors.toList());
    }

    @Override
    public Page<TDTO> findAll(Pageable pageable, UserDetails details) {
        return getDao().findAll(pageable).map(item -> toDTO(item, false));
    }

    @Override
    public void delete(TPK id) {
        try{
            getDao().delete(id);
        }catch (ConstraintViolationException e){
            throw new BadRequestException("constraint.violation", new Throwable("constraint.violation"));
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException("not.found", new Throwable("not.found"));
        }catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("constraint.violation", new Throwable("constraint.violation"));
        }

    }

    @Override
    public void delete(TEntity entity) {
        try{
            getDao().delete(entity);
        }catch (ConstraintViolationException e){
            throw new BadRequestException("constraint.violation", new Throwable("constraint.violation"));
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException("constraint.violation", new Throwable("constraint.violation"));
        }catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("constraint.violation", new Throwable("constraint.violation"));
        }
    }

    //================================================================================================================//
    protected TDTO toDTO(TEntity item, boolean detailed) {
        try {
            return dto.getConstructor(entity, boolean.class).newInstance(item, detailed);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(dto.getName() + ": No constructor matches (TEntity, Boolean)");
        }
    }
}
