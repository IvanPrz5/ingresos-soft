package com.ingresos_soft.Facturacion.Models.Sat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TasaCuota")
public class TasaCuotaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String factor;

    @Column(length = 30, nullable = false)
    private String impuesto;

    @Column(length = 30, nullable = false)
    private String rangoFijo;

    @Column(length = 10, nullable = false)
    private String retencion;

    @Column(length = 10, nullable = false)
    private String traslado;

    @Column(length = 20, nullable = false)
    private BigDecimal valorMaximo;

    @Column(length = 20)
    private BigDecimal valorMinimo;

    @Column
    private Boolean status;

    public TasaCuotaModel(String factor,
            String impuesto,
            String rangoFijo,
            String retencion,
            String traslado,
            BigDecimal valorMaximo,
            BigDecimal valorMinimo,
            Boolean status) {
        this.factor = factor;
        this.impuesto = impuesto;
        this.rangoFijo = rangoFijo;
        this.retencion = retencion;
        this.traslado = traslado;
        this.valorMaximo = valorMaximo;
        this.valorMinimo = valorMinimo;
        this.status = status;
    }
}
