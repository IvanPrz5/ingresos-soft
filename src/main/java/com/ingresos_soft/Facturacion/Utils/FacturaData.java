package com.ingresos_soft.Facturacion.Utils;

import com.ingresos_soft.Facturacion.Dto.Factura.ConceptosFacturaDto;
import com.ingresos_soft.Facturacion.Models.Factura.ImpuestosFacturaModel;
import com.ingresos_soft.Facturacion.Models.Sat.FormaPagoModel;
import com.ingresos_soft.Facturacion.Models.Sat.MetodoPagoModel;
import com.ingresos_soft.Facturacion.Models.Sat.TipoComprobanteModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FacturaData {
    private TipoComprobanteModel tipoComprobante;
    private MetodoPagoModel metodoPago;
    private FormaPagoModel formaPago;
    private LocalDateTime fecha;
    private String folio;
    private String serie;
    private List<ConceptosFacturaDto> conceptosFacturaList;
    private List<ImpuestosFacturaModel> impuestosFacturaLocales;
}
