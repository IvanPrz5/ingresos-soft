package com.ingresos_soft.Universal.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenusDto {
    private Long id;
    private String title;
    private String icon;
    private String ruta;
    private Boolean status;

    private List<MenusDto> children;
}