package com.ingresos_soft.Auth.JWT;

import java.io.IOException;
import java.util.Collections;

import com.ingresos_soft.Auth.Services.UserDetailImp;
import com.ingresos_soft.Auth.Utils.Data.AuthCredentials;
import com.ingresos_soft.Auth.Utils.Token.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();

        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {

        }

        UsernamePasswordAuthenticationToken usernamePat = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmail(),
                authCredentials.getPassword(),
                Collections.emptyList());

        return getAuthenticationManager().authenticate(usernamePat);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailImp userDetail = (UserDetailImp) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetail.getNombre(), userDetail.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}