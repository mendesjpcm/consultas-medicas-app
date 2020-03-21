package com.example.medicalappointments.persistence.models;

import com.example.medicalappointments.persistence.models.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_user", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "email")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationUser extends BaseEntity implements UserDetails {

    @Getter
    @Setter
    @Column(nullable = false, length = 50)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false, length = 50)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String username;

    @Getter
    @Setter
    @Column(nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean	enabled = true;

    @Getter
    @Setter
    @Column(name = "account_non_expired", nullable = false)
    private boolean	accountNonExpired = true;

    @Getter
    @Setter
    @Column(name = "account_non_locked", nullable = false)
    private boolean	accountNonLocked = true;

    @Getter
    @Setter
    @Column(name = "credentials_non_expired", nullable = false)
    private boolean	credentialsNonExpired = true;

    @Column(columnDefinition = "TEXT")
    private String authorities;

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = StringUtils.join(authorities, "|");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities != null && !authorities.isEmpty()? Arrays.stream(authorities.split("\\|")).map(SimpleGrantedAuthority::new).collect(Collectors.toList()) : new ArrayList<>();
    }
}
