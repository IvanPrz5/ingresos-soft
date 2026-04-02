package com.ingresos_soft.Universal.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolesDto {
    private Long id;
    private String descripcion;
    private Boolean status;
}