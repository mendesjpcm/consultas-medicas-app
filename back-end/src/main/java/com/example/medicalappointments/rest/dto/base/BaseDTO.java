package com.example.medicalappointments.rest.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;

public abstract class BaseDTO<T> implements IBaseDTO<T> {
    @Getter(value = AccessLevel.PROTECTED, onMethod = @__({@JsonIgnore}))
    @Setter(value = AccessLevel.PROTECTED, onMethod = @__({@JsonIgnore}))
    private ModelMapper mapper;

    @Getter
    @Setter
    @NotNull
    @JsonProperty("id")
    private Long id;

    public BaseDTO(){
        setMapper(new ModelMapper());
        getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public BaseDTO(T entity){
        setMapper(new ModelMapper());
        getMapper().getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        getMapper().getConfiguration().setSkipNullEnabled(true);
        getMapper().map(entity, this);
    }

    @SuppressWarnings("unchecked")
    @JsonIgnore
    @Override
    public T getModel(){
        try{
            T model = ((Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            getMapper().map(this, model);
            return model;
        } catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}
