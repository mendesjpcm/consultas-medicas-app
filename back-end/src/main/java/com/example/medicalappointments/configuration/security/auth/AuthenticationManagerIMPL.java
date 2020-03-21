package com.example.medicalappointments.configuration.security.auth;

import com.example.medicalappointments.configuration.security.AuthenticationBO;
import com.example.medicalappointments.persistence.models.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerIMPL implements AuthenticationManager {
    private	final AuthenticationBO authBO;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthenticationManagerIMPL(AuthenticationBO bo, PasswordEncoder encoder) {
        this.authBO 	= bo;
        this.encoder	= encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String			username		= (String) authentication.getPrincipal();
//		String			password		= Hashing.sha256().hashString((String) authentication.getCredentials(), StandardCharsets.UTF_8).toString();
        String 			password = (String) authentication.getCredentials();
        ApplicationUser user			=(ApplicationUser) authBO.loadUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Usuário ou senha inválido(s).",new Throwable("user.or.password.mismatch"));

        if (!user.isEnabled())
            throw new DisabledException("Usuário está desabilitado",new Throwable("user.disabled"));

//        if (!encoder.matches(password, user.getPassword()))
//            throw new BadCredentialsException("Usuário ou senha inválido(s).", new Throwable("user.or.password.mismatch"));

//        if(!user.getPassword().equals(password))
//            throw new BadCredentialsException("Usuário ou senha inválido(s).", new Throwable("user.or.password.mismatch"));

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }


    public Authentication reauthenticate(Authentication authentication) throws AuthenticationException {
        String username	= (String) authentication.getPrincipal();
        ApplicationUser	user = (ApplicationUser) authBO.loadUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("user.notfound");

        if (!user.isEnabled())
            throw new DisabledException("user.disabled");

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    }
}
