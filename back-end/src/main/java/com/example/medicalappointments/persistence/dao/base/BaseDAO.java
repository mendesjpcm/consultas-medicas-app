package com.example.medicalappointments.persistence.dao.base;

import com.example.medicalappointments.exceptions.BadRequestException;
import com.example.medicalappointments.exceptions.NotFoundException;
import com.example.medicalappointments.persistence.repository.base.IBase;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BaseDAO <Entity, ID extends Serializable, Repository extends IBase<Entity, ID>> implements IBaseDAO<Entity, ID>{
    @Getter(AccessLevel.PROTECTED)
    private final Repository repository;

    @Inject
    protected BaseDAO(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Entity findOne(ID id) {
        return repository.findById(Optional.ofNullable(id).orElseThrow(() -> new BadRequestException("id.not.specified"))).orElseThrow(() -> new NotFoundException("id.not.found"));
    }

    @Override
    public Iterable<Entity> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Page<Entity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Entity save(Entity model) {
        return repository.save(model);
    }

    @Override
    public Iterable<Entity> save(Iterable<Entity> models) {
        return repository.saveAll(models);
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Entity entity) {
        repository.delete(entity);
    }
}
