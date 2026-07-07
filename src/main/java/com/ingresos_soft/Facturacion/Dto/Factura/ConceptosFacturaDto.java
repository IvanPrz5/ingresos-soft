package com.ingresos_soft.Facturacion.Dto.Factura;

import com.ingresos_soft.Facturacion.Models.Sat.ObjetoImpuestoModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ConceptosFacturaDto {
    private Long id;
    private BigDecimal cantidad;
    private String descripcion;
    private BigDecimal valorUnitario;
    private BigDecimal importe;
    private BigDecimal descuento;
    private Boolean isPrecargado;
    private Boolean status;
    private ObjetoImpuestoModel idObjetoImp;
    private List<ImpuestosFacturaDto> impuestosFacturaList;
}
