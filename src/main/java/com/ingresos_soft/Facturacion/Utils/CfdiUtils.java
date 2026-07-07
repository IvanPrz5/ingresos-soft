package com.ingresos_soft.Facturacion.Utils;

import com.ingresos_soft.Facturacion.Dto.Factura.ImpuestosFacturaDto;
import com.ingresos_soft.Facturacion.Models.Factura.ImpuestosFacturaModel;
import com.ingresos_soft.Facturacion.Utils.cfdi40.*;
import com.ingresos_soft.Ingresos.Models.Core.ContribuyenteModel;
import com.ingresos_soft.Ingresos.Models.Core.EntidadesModel;
import lombok.extern.slf4j.Slf4j;

import com.ingresos_soft.Facturacion.Utils.cfdi40.CEstado;
import com.ingresos_soft.Facturacion.Utils.cfdi40.CMetodoPago;
import com.ingresos_soft.Facturacion.Utils.cfdi40.CMoneda;
import com.ingresos_soft.Facturacion.Utils.cfdi40.CTipoDeComprobante;
import com.ingresos_soft.Facturacion.Utils.cfdi40.CUsoCFDI;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante.CfdiRelacionados;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante.Complemento;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante.Conceptos;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante.Emisor;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante.Receptor;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante.CfdiRelacionados.CfdiRelacionado;
import com.ingresos_soft.Facturacion.Utils.cfdi40.Comprobante.Conceptos.Concepto;
import com.ingresos_soft.Facturacion.Utils.cfdi40.DefaultNamespacePrefixMapper4;
import com.ingresos_soft.Facturacion.Utils.cfdi40.ObjectFactory;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CfdiUtils {

    public ByteArrayOutputStream createCfdi(EntidadesModel entidad) {
        try {
            return null;
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateCfdi, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    private Comprobante createComprobanteNode(ObjectFactory oF,
            EntidadesModel entidad) {
        try {
            log.info("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateComprobanteNode, Iniciando: ");

            Comprobante comprobante = oF.createComprobante();
            comprobante.setVersion(ReglasNegocioUtils.version());
            comprobante.setExportacion(ReglasNegocioUtils.exportacion());
            comprobante.setMoneda(CMoneda.MXN);
            //comprobante.setTipoDeComprobante(CTipoDeComprobante.I);
            //comprobante.setMetodoPago(CMetodoPago.PUE);
            comprobante.setLugarExpedicion(String.valueOf(entidad.getCodigoPostal()));

            ZonedDateTime nowInMexico = ZonedDateTime.now(ZoneId.of("America/Mexico_City"))
                                                     .withNano(0);
            GregorianCalendar calendar = GregorianCalendar.from(nowInMexico);
            XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance()
                                                         .newXMLGregorianCalendar(calendar);
            xmlCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            xmlCal.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            comprobante.setFecha(xmlCal);

            return comprobante;

        } catch (Exception e) {
            log.error("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateComprobanteNode, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    private void createEmisorNode(ObjectFactory oF,
            Comprobante comprobante,
            EntidadesModel entidad) {
        try {
            log.info("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateEmisorNode, Iniciando: ");
            Emisor emisor = oF.createComprobanteEmisor();
            emisor.setRfc(entidad.getRfc());
            emisor.setNombre(entidad.getRazonSocial());
            emisor.setRegimenFiscal(entidad.getIdRegimenFiscal()
                                           .getClave());
            comprobante.setEmisor(emisor);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateEmisorNode, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    private void createReceptorNode(ObjectFactory oF,
            EntidadesModel entidad,
            ContribuyenteModel contribuyente,
            Comprobante comprobante) {
        try {
            log.info("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateReceptorNode, Iniciando: ");
            Receptor receptor = oF.createComprobanteReceptor();
            receptor.setNombre(contribuyente.getPersonaFisica()
                               ? contribuyente.getNombreCompleto()
                               : contribuyente.getRazonSocial());

            receptor.setRfc(contribuyente.getRfc());
            receptor.setDomicilioFiscalReceptor(ReglasNegocioUtils.rfcGenerico()
                                                                  .equals(contribuyente.getRfc())
                                                ? entidad.getCodigoPostal()
                                                : contribuyente.getRfc());
            receptor.setRegimenFiscalReceptor(contribuyente.getIdRegimenFiscal()
                                                           .getClave());
            //receptor.setUsoCFDI(CUsoCFDI.CN_01);

            comprobante.setReceptor(receptor);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateReceptorNode, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void createNodeConceptos(ObjectFactory oF,
            FacturaData facturaData) {
        try {
            log.info("Plugin: Facturacion, Utils: CfdiUtils, Method: createNodeConceptos, Iniciando: ");

            Conceptos conceptos = oF.createComprobanteConceptos();

            for (int i = 0; i < facturaData.getConceptosFacturaList()
                                           .size(); i++) {
                Concepto concepto = oF.createComprobanteConceptosConcepto();

                concepto.setClaveProdServ("");
                concepto.setCantidad(facturaData.getConceptosFacturaList()
                                                .get(i)
                                                .getCantidad());
                concepto.setClaveUnidad("");
                concepto.setUnidad("");
                concepto.setDescripcion(facturaData.getConceptosFacturaList()
                                                   .get(i)
                                                   .getDescripcion());
                concepto.setObjetoImp(facturaData.getConceptosFacturaList()
                                                 .get(i)
                                                 .getIdObjetoImp()
                                                 .getClave());
                concepto.setValorUnitario(facturaData.getConceptosFacturaList()
                                                     .get(i)
                                                     .getValorUnitario());
                concepto.setImporte(facturaData.getConceptosFacturaList()
                                               .get(i)
                                               .getImporte());
                concepto.setDescuento(facturaData.getConceptosFacturaList()
                                                 .get(i)
                                                 .getDescuento());

                if (!facturaData.getConceptosFacturaList()
                                .get(i)
                                .getIdObjetoImp()
                                .getClave()
                                .equals("01")) {
                    Concepto.Impuestos impuestos = oF.createComprobanteConceptosConceptoImpuestos();

                    List<ImpuestosFacturaDto> impuestosFacturaList = facturaData.getConceptosFacturaList()
                                                                                .get(i)
                                                                                .getImpuestosFacturaList();

                    boolean tieneTraslados = impuestosFacturaList.stream()
                                                                 .anyMatch(impuesto -> !impuesto.getIsRetencion());

                    boolean tieneRetenciones = impuestosFacturaList.stream()
                                                                   .anyMatch(ImpuestosFacturaDto::getIsRetencion);

                    if (tieneTraslados) {
                        Concepto.Impuestos.Traslados traslados = oF.createComprobanteConceptosConceptoImpuestosTraslados();
                    }

                    if (tieneRetenciones) {
                        Concepto.Impuestos.Retenciones retenciones = oF.createComprobanteConceptosConceptoImpuestosRetenciones();
                    }

                    for (ImpuestosFacturaDto impuesto : impuestosFacturaList) {

                        if (impuesto.getIsRetencion()) {

                            Concepto.Impuestos.Retenciones.Retencion retencion = oF.createComprobanteConceptosConceptoImpuestosRetencionesRetencion();

                            retencion.setBase(impuesto.getBase());
                            retencion.setImpuesto(impuesto.getIdImpuesto()
                                                          .getClave());
                            retencion.setTipoFactor(CTipoFactor.fromValue(impuesto.getIdTipoFactor()
                                                                                  .getDescripcion()));
                            retencion.setTasaOCuota(impuesto.getIdTasaCuota()
                                                            .getValorMaximo());
                            retencion.setImporte(impuesto.getImporte());

                        } else {

                            Concepto.Impuestos.Traslados.Traslado traslado = oF.createComprobanteConceptosConceptoImpuestosTrasladosTraslado();

                            traslado.setBase(impuesto.getBase());
                            traslado.setImpuesto(impuesto.getIdImpuesto()
                                                         .getClave());
                            traslado.setTipoFactor(CTipoFactor.fromValue(impuesto.getIdTipoFactor()
                                                                                 .getDescripcion()));
                            traslado.setTasaOCuota(impuesto.getIdTasaCuota()
                                                           .getValorMaximo());
                            traslado.setImporte(impuesto.getImporte());
                        }
                    }

                }

            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Utils: CfdiUtils, Method: CreateNodeConceptos, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void createNodeImpuestos(ObjectFactory oF,
            FacturaData facturaData) {
        try {
            Comprobante.Impuestos impuestos = oF.createComprobanteImpuestos();

            BigDecimal totalRetenciones = facturaData.getImpuestosFacturaLocales()
                                                     .stream()
                                                     .filter(r -> Boolean.TRUE.equals(r.getIsRetencion()))
                                                     .map(ImpuestosFacturaModel::getImporte)
                                                     .reduce(BigDecimal.ZERO,
                                                             BigDecimal::add);

            BigDecimal totalTraslados = facturaData.getImpuestosFacturaLocales()
                                                   .stream()
                                                   .filter(t -> !Boolean.TRUE.equals(t.getIsRetencion()))
                                                   .map(ImpuestosFacturaModel::getImporte)
                                                   .reduce(BigDecimal.ZERO,
                                                           BigDecimal::add);


        } catch (Exception e) {
            log.error("Plugin: Facturacion, utils: CfdiUtils, Method: CreateNodeImpuestos, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

}
