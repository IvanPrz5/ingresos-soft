package com.ingresos_soft.Facturacion.Models.Sat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MetodoPago")
public class MetodoPagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String clave;

    @Column(length = 200, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean status;

    public MetodoPagoModel(String clave,
            String descripcion,
            Boolean status) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.status = status;
    }
}
