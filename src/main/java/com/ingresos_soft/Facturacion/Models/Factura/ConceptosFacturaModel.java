package com.ingresos_soft.Facturacion.Models.Factura;

import com.ingresos_soft.Facturacion.Models.Sat.ObjetoImpuestoModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ConceptosFactura")
public class ConceptosFacturaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal cantidad;

    @Column(length = 1000, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private BigDecimal importe;

    @Column(nullable = false)
    private BigDecimal descuento;

    @Column(nullable = false)
    private Boolean isPrecargado;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idObjetoImp", nullable = false)
    private ObjetoImpuestoModel idObjetoImp;

    @ManyToOne
    @JoinColumn(name = "idFactura")
    private FacturaModel idFactura;
    // @ManyToOne
    // @JoinColumn(name = "idClaveProdServ", nullable = false)
}
