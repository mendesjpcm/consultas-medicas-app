package com.example.medicalappointments.configuration.security;

import com.example.medicalappointments.configuration.security.auth.AuthenticationEntry;
import com.example.medicalappointments.configuration.security.auth.AuthenticationFilter;
import com.example.medicalappointments.configuration.security.auth.jwt.JWTTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {
    private	final AuthenticationBO service;
    private	final AuthenticationEntry entry;
    private	final JWTTokenHelper token;

    @Autowired
    public Security(AuthenticationEntry entry, JWTTokenHelper token, AuthenticationBO service, AuthenticationManagerBuilder builder) throws Exception {
        this.service	= service;
        this.entry		= entry;
        this.token		= token;
        builder.userDetailsService(this.service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //accepted values: .permitAll() or .authenticated
//                .antMatchers("/rest/**", "/auth/changePassword").authenticated()
                .antMatchers("/rest/**").authenticated()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                .addFilterBefore(new AuthenticationFilter(token, service), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(entry)
                .and()
                .httpBasic()
                .authenticationEntryPoint(entry)
                .and().cors()
                .and().csrf().disable();//csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS)
                .antMatchers(HttpMethod.POST, "/auth/login")
                .antMatchers("/assets/**", "/webjars/**", "/api-docs/**", "/configuration/ui")
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs");
    }
}
