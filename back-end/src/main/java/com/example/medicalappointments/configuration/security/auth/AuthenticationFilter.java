package com.example.medicalappointments.configuration.security.auth;

import com.example.medicalappointments.configuration.security.AuthenticationBO;
import com.example.medicalappointments.configuration.security.auth.jwt.JWTException;
import com.example.medicalappointments.configuration.security.auth.jwt.JWTTokenHelper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JWTTokenHelper helper;
    private final AuthenticationBO authBO;

    public static Cache<String, String> saltCache;

    @Autowired
    public AuthenticationFilter(JWTTokenHelper helper, AuthenticationBO bo) {
        this.helper = helper;
        this.authBO = bo;

        if (AuthenticationFilter.saltCache == null)
            AuthenticationFilter.saltCache = initializeSaltCache();
    }

    public Cache<String, String> initializeSaltCache() {
        return CacheBuilder.newBuilder().maximumSize(100 * 1000).expireAfterWrite(20, TimeUnit.MINUTES).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            String token;
            UserDetails details;

            if ((token = helper.getToken(request)) != null
                    && ((details = authBO.loadUserByUsername(helper.getUserLogin(token)))) != null) {
                AuthenticationToken authentication = new AuthenticationToken(details);
                authentication.setToken(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // VALIDATE TOKEN
                if (helper.isExpired(token, details))
//					throw new JWTException("session.expired");
                    throw new RuntimeException("Sua sessão expirou. Efetue login novamente.", new Throwable("session.expired"));

                // CSRF Attack Prevention
                String csrfKey = helper.getSessionID(token);
                try {
                    // debug
                    if (AuthenticationFilter.saltCache.getIfPresent(csrfKey) != null) {
                        if (AuthenticationFilter.saltCache.getIfPresent(csrfKey) == null)
//							throw new JWTException("session.expired");
                            throw new RuntimeException("Sua sessão expirou. Efetue login novamente.", new Throwable("session.expired"));

                        if ((!saltCache.asMap().get(csrfKey).isEmpty()
                                && !saltCache.asMap().get(csrfKey).equals(request.getHeader("csrf")))) {
                            saltCache.invalidate(csrfKey);
                            //throw new JWTException("ilegal.signture");
                        }
                    }
                } finally {
                    if (AuthenticationFilter.saltCache.getIfPresent(csrfKey) != null) {
                        String salt = String.valueOf(new SecureRandom().nextLong());
                        AuthenticationFilter.saltCache.put(csrfKey, salt);
                        response.addHeader("csrf", salt);
                    }
                }

            }

            chain.doFilter(request, response);

        } catch (MalformedJwtException | ExpiredJwtException | JWTException e) {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } catch (IOException | ServletException | BadCredentialsException | UsernameNotFoundException e) {
            if (e instanceof UsernameNotFoundException)
                throw new UsernameNotFoundException("username.not.found");
            e.printStackTrace();
        }
    }
}
