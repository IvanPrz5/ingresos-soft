package com.ingresos_soft.Auth.Controllers;

import java.util.HashMap;
import java.util.Map;

import com.ingresos_soft.Auth.Services.AuthService;
import com.ingresos_soft.Auth.Utils.Data.AuthCredentials;
import com.ingresos_soft.Auth.Utils.Data.AuthRegisterCredentials;
import com.ingresos_soft.Universal.Services.UsuariosRolesService;
import com.ingresos_soft.Universal.Utils.MessageResponse;
import com.ingresos_soft.Universal.Utils.ResultObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/Auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UsuariosRolesService usuariosRolesService;

    @GetMapping("/isAuthorizated/{id}")
    public ResponseEntity<ResultObjectResponse> isAuthorizated(@PathVariable("id") Long id) {
        try {
            Map<String, Object> responseMap = new HashMap<>();

            Boolean authorizatedUser = usuariosRolesService.isAuthorizated(id);
            if (authorizatedUser) {
                responseMap.put("Autorizado", authorizatedUser);
            } else {
                responseMap.put("Autorizado", authorizatedUser);
            }
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(new ResultObjectResponse(true, "Transaccion Exitosa", responseMap));
        } catch (Exception e) {
            log.error("Plugin: Auth, Controller: AuthController, Method: IsAuthorizated, Error: ", e);
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ResultObjectResponse(false, "Usuario no autorizado", null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResultObjectResponse> authenticateUser(@RequestBody AuthCredentials authCredentials) {
        try {
            Map<String, Object> authResponse = authService.authenticateUser(authCredentials);

            boolean hasError = authResponse.containsKey("error");
            if (hasError) {
                return ResponseEntity
                        .status(HttpStatus.NOT_ACCEPTABLE)
                        .body(MessageResponse.error(null, authResponse.get("error").toString()));
            }

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(new ResultObjectResponse(true, "Bienvenido",
                                                   authResponse));
        } catch (Exception ex) {
            log.error("Plugin: Auth, Controller: AuthController, Method: AuthenticateUser, Error : ", ex);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResultObjectResponse(false, "Por favor revisa tus credenciales",
                                                   null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResultObjectResponse> register(@RequestBody AuthRegisterCredentials authRegisterCredentials) {
        try {
            Map<String, String> authResponse = authService.register(authRegisterCredentials);

            boolean hasError = authResponse.containsKey("error");
            if (hasError) {
                return ResponseEntity
                        .status(HttpStatus.NOT_ACCEPTABLE)
                        .body(MessageResponse.error(null, authResponse.get("error").toString()));
            }

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(new ResultObjectResponse(true, "Bienvenido",
                                                   authResponse));
        } catch (Exception e) {
            log.error("Plugin: Auth, Controller: AuthController, Method: Register, Error : ", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResultObjectResponse(false, "Por favor revisa tus credenciales",
                                                   null));
        }
    }
}