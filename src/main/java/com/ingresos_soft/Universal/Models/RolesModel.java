package com.ingresos_soft.Universal.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Roles")
public class RolesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean status;

    public RolesModel(String descripcion, Boolean status) {
        this.descripcion = descripcion;
        this.status = status;
    }
}