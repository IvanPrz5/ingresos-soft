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
@Table(name = "Modules")
public class ModulesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 150, nullable = false)
    private String descripcion;

    @Column(length = 200, nullable = false)
    private String image;

    @Column(length = 150, nullable = false)
    private String path;

    @Column(nullable = false)
    private Boolean status;

    public ModulesModel(String nombre, String descripcion, String image, String path, Boolean status) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.image = image;
        this.path = path;
        this.status = status;
    }
}