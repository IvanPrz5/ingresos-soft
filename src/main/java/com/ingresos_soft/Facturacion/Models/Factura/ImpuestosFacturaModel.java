package com.ingresos_soft.Facturacion.Models.Factura;

import com.ingresos_soft.Facturacion.Models.Sat.ImpuestoModel;
import com.ingresos_soft.Facturacion.Models.Sat.TasaCuotaModel;
import com.ingresos_soft.Facturacion.Models.Sat.TipoFactorModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ImpuestosFactura")
public class ImpuestosFacturaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal base;

    @Column(nullable = false)
    private BigDecimal importe;

    @Column(nullable = false)
    private Boolean isLocal;

    @Column(nullable = false)
    private Boolean isRetencion;

    @Column(nullable = false)
    private Boolean isPrecargado;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idImpuesto")
    private ImpuestoModel idImpuesto;

    @ManyToOne
    @JoinColumn(name = "idTipoFactor")
    private TipoFactorModel idTipoFactor;

    @ManyToOne
    @JoinColumn(name = "idTasaCuota")
    private TasaCuotaModel idTasaCuota;

    @ManyToOne
    @JoinColumn(name = "idConceptoFactura")
    private ConceptosFacturaModel idConceptoFactura;
}
