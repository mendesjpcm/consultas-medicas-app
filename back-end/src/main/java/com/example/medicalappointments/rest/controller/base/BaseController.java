package com.example.medicalappointments.rest.controller.base;

import com.example.medicalappointments.rest.service.base.IBaseBO;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

public abstract class BaseController <T extends IBaseBO<?, TDTO, Long>, TDTO> {

    @Getter(AccessLevel.PROTECTED)
    private final T service;

    public BaseController(T service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id, @ApiIgnore @AuthenticationPrincipal UserDetails details){
        return ResponseEntity.ok(service.findOne(id,details));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<?> findAll(@ApiIgnore @AuthenticationPrincipal UserDetails details){
        return ResponseEntity.ok(service.findAll(details));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/page")
    public ResponseEntity<?> findAll(Pageable pageable, @ApiIgnore @AuthenticationPrincipal UserDetails details){
        return ResponseEntity.ok(service.findAll(pageable, details));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<?> save(@RequestBody @Valid TDTO dto, @ApiIgnore @AuthenticationPrincipal UserDetails details){
        return ResponseEntity.ok(service.save(dto, details));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/")
    public ResponseEntity<?> update(@RequestBody @Valid TDTO dto, @ApiIgnore @AuthenticationPrincipal UserDetails details){
        return ResponseEntity.ok(service.update(dto, details));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id, @ApiIgnore @AuthenticationPrincipal UserDetails details){
        getService().delete(id);
        return ResponseEntity.ok(null);
    }
}
