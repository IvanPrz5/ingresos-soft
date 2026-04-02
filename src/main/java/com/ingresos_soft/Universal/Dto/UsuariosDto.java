package com.ingresos_soft.Universal.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosDto {
    private Long id;
    private String nombre;
    private String apPaterno;
    private String email;
    private String password;
    private LocalDate fechaNacimiento;
    private Long telefono;
    private Boolean status;
    private List<RolesDto> role = new ArrayList<>();
}