package com.ingresos_soft.Facturacion.Models.Factura;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "FacturaDetails")
public class FacturaDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String serie;

    @Column
    private String folio;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal descuento;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String cadenaOriginalSat;

    @Column(nullable = false)
    private String noCertificadoSat;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String selloSat;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String selloCfdi;
}
