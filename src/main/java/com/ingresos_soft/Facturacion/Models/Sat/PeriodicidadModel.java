package com.ingresos_soft.Facturacion.Models.Sat;

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
@Table(name = "Periodicidad")
public class PeriodicidadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String clave;

    @Column(length = 200, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean status;

    public PeriodicidadModel(String clave, String descripcion, Boolean status) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.status = status;
    }

}
