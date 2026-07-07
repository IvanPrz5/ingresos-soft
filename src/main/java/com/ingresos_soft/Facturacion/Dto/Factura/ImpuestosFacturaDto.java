package com.ingresos_soft.Facturacion.Dto.Factura;

import com.ingresos_soft.Facturacion.Models.Sat.ImpuestoModel;
import com.ingresos_soft.Facturacion.Models.Sat.TasaCuotaModel;
import com.ingresos_soft.Facturacion.Models.Sat.TipoFactorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ImpuestosFacturaDto {
    private Long id;
    private String descripcion;
    private BigDecimal base;
    private BigDecimal importe;
    private Boolean isLocal;
    private Boolean isRetencion;
    private Boolean isPrecargado;
    private Boolean status;
    private ImpuestoModel idImpuesto;
    private TipoFactorModel idTipoFactor;
    private TasaCuotaModel idTasaCuota;
}
