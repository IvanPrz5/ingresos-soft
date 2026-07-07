package com.ingresos_soft.Facturacion.Models.Sat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Impuesto")
public class ImpuestoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String clave;

    @Column(length = 20, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String localFederal;

    @Column(nullable = false)
    private String retencion;

    @Column(nullable = false)
    private String traslado;

    @Column(nullable = false)
    private Boolean status;

    public ImpuestoModel(String clave,
            String descripcion,
            String localFederal,
            String retencion,
            String traslado,
            Boolean status) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.localFederal = localFederal;
        this.retencion = retencion;
        this.traslado = traslado;
        this.status = status;
    }
}
