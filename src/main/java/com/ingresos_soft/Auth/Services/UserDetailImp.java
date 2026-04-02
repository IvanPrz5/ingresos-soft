package com.ingresos_soft.Auth.Services;

import java.util.Collection;
import java.util.Collections;

import com.ingresos_soft.Universal.Models.UsuariosModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailImp implements UserDetails {

    private final UsuariosModel usuariosModel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usuariosModel.getPassword();
    }

    @Override
    public String getUsername() {
        return usuariosModel.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return usuariosModel.getNombre();
    }

    public UsuariosModel getUser() {
        return usuariosModel;
    }

    public Long getId() {
        return usuariosModel.getId();
    }
}