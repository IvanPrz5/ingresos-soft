package com.ingresos_soft.Ingresos.Models.Core;

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
@Table(name = "Archivo")
public class ArchivoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private byte[] contenido;

    @Column(length = 150, nullable = false)
    private String tipoArchivo;

    @Column(length = 250, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean status;

    public ArchivoModel(byte[] contenido, String tipoArchivo, String nombre, Boolean status) {
        this.contenido = contenido;
        this.tipoArchivo = tipoArchivo;
        this.nombre = nombre;
        this.status = status;
    }
}