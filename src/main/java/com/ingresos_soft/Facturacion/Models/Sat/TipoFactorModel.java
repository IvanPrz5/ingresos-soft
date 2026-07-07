package com.ingresos_soft.Facturacion.Models.Sat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TipoFactor")
public class TipoFactorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean status;

    public TipoFactorModel(String descripcion,
            Boolean status) {
        this.descripcion = descripcion;
        this.status = status;
    }
}
