package com.ingresos_soft.Auth.Services;

import com.ingresos_soft.Universal.Models.UsuariosModel;
import com.ingresos_soft.Universal.Repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuariosModel usuariosModel = usuariosRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con emal " + email + " no existe"));

        return new UserDetailImp(usuariosModel);
    }
}