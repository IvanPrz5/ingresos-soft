package com.ingresos_soft.Auth.Utils.Data;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AuthRegisterCredentials {
    private String nombre;
    private String apPaterno;
    private String email;
    private String password;
    private LocalDate fechaNacimiento;
    private Long telefono;
}