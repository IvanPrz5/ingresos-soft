package com.ingresos_soft.Facturacion.Models.Sat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TipoComprobante")
public class TipoComprobanteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String clave;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean status;

    public TipoComprobanteModel(String clave,
            String descripcion,
            Boolean status) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.status = status;
    }
}
