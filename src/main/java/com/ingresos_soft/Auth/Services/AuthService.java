package com.ingresos_soft.Auth.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.ingresos_soft.Auth.Utils.Data.AuthCredentials;
import com.ingresos_soft.Auth.Utils.Data.AuthRegisterCredentials;
import com.ingresos_soft.Auth.Utils.Token.TokenUtils;
import com.ingresos_soft.Universal.Dto.UsuariosDto;
import com.ingresos_soft.Universal.Models.UsuariosModel;
import com.ingresos_soft.Universal.Services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuariosService usuariosService;

    public Map<String, Object> authenticateUser(AuthCredentials authCredentials) {
        try {
            Map<String, Object> response = new HashMap<>();
            Optional<UsuariosModel> usuarioOptional = usuariosService
                    .findOneByEmail(authCredentials.getEmail());

            if (!usuarioOptional.isPresent()) {
                response.put("error", "Usuario no encontrado. El email no existe.");
                return response;
            }

            UsuariosModel usuarioInstance = usuarioOptional.get();

            String token = generateToken(authCredentials);
            UsuariosDto usuarioDto = usuariosService.convertToDto(usuarioInstance);
            response.put("token", token);
            response.put("usuario", usuarioDto);

            return response;
        } catch (Exception e) {
            log.error("Plugin: Auth, Service: AuthService, Method: AuthenticateUser, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public Map<String, String> register(AuthRegisterCredentials request) {
        try {
            Map<String, String> response = new HashMap<>();

            Optional<UsuariosModel> usuarioOptional = usuariosService.findOneByEmail(request.getEmail());

            if (usuarioOptional.isPresent()) {
                response.put("error", "El email ya existe. Por favor, utiliza otro email.");
                return response;
            }

            UsuariosModel usuarioInstance = new UsuariosModel();
            usuarioInstance.setNombre(request.getNombre());
            usuarioInstance.setApPaterno(request.getApPaterno());
            usuarioInstance.setEmail(request.getEmail());
            usuarioInstance.setPassword(request.getPassword());
            usuarioInstance.setFechaNacimiento(request.getFechaNacimiento());
            usuarioInstance.setTelefono(request.getTelefono());
            usuarioInstance.setStatus(true);

            if (usuariosService.saveOrUpdate(usuarioInstance) == null) {
                response.put("error", "Error al registrar el usuario. Intenta nuevamente.");
                return response;
            } else {
                response.put("success", "Usuario registrado correctamente.");
                return response;
            }

        } catch (Exception e) {
            log.error("Plugin: Auth, Service: AuthService, Method: Register, Error: ", e);
            throw new NoSuchElementException();
        }
    }

    public UsuariosModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailImp) {
            return ((UserDetailImp) principal).getUser();
        }
        return null;
    }

    public String generateToken(AuthCredentials authCredentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authCredentials.getEmail().replaceAll("\\s", ""),
                            authCredentials.getPassword().replaceAll("\\s", "")));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailImp userDetails = (UserDetailImp) authentication.getPrincipal();

            String token = TokenUtils.createToken(
                    userDetails.getNombre(),
                    userDetails.getUsername() // Solo el email
            );
            return token;
        } catch (Exception e) {
            log.error("Plugin: Auth, Service: AuthService, Method: GenerateToken, Error: ", e);
            throw new NoSuchElementException();
        }
    }
}
