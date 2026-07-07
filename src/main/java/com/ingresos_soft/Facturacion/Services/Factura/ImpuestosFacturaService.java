package com.ingresos_soft.Facturacion.Services.Factura;

import com.ingresos_soft.Facturacion.Dto.Factura.ImpuestosFacturaDto;
import com.ingresos_soft.Facturacion.Models.Factura.ImpuestosFacturaModel;
import com.ingresos_soft.Facturacion.Repositories.Factura.ImpuestosFacturaRepository;
import com.ingresos_soft.Facturacion.Repositories.Sat.ImpuestoRepository;
import com.ingresos_soft.Facturacion.Repositories.Sat.TasaCuotaRepository;
import com.ingresos_soft.Facturacion.Repositories.Sat.TipoFactorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class ImpuestosFacturaService {

    @Autowired
    ImpuestosFacturaRepository impuestosFacturaRepository;

    @Autowired
    ImpuestoRepository impuestoRepository;

    @Autowired
    TipoFactorRepository tipoFactorRepository;

    @Autowired
    TasaCuotaRepository tasaCuotaRepository;

    public ImpuestosFacturaModel get(Long id) {
        return impuestosFacturaRepository.findById(id)
                                         .orElseThrow();
    }

    public ImpuestosFacturaDto findById(Long id) {
        try {
            return this.convertToDto(this.get(id));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ImpuestosFacturaService, Metohd: FindById, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public List<ImpuestosFacturaDto> findByIdConceptoFactura(Long idConceptoFactura) {
        try {
            return impuestosFacturaRepository.findByIdConceptoFactura_Id(idConceptoFactura)
                                             .stream()
                                             .map(this::convertToDto)
                                             .toList();
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ImpuestosFacturaService, Method: FindByIdConceptoFactura, Error: ",
                      e);
            throw new NoSuchElementException();
        }
    }

    public void saveOrUpdate(ImpuestosFacturaModel request) {
        try {
            ImpuestosFacturaModel impuestoFacturaInstance;

            if (request.getId() != null) {
                impuestoFacturaInstance = this.get(request.getId());
                impuestoFacturaInstance.setStatus(request.getStatus());
            } else {
                impuestoFacturaInstance = new ImpuestosFacturaModel();
                impuestoFacturaInstance.setStatus(true);
            }

            impuestoFacturaInstance.setBase(request.getBase());
            impuestoFacturaInstance.setImporte(request.getImporte());

            /* false = traslado *--*--*--* true = retencion*/
            impuestoFacturaInstance.setIsRetencion(request.getIsRetencion());

            impuestoFacturaInstance.setIsPrecargado(request.getIsPrecargado());
            impuestoFacturaInstance.setIdImpuesto(impuestoRepository.findById(request.getIdImpuesto()
                                                                                     .getId())
                                                                    .orElseThrow());
            impuestoFacturaInstance.setIdTipoFactor(tipoFactorRepository.findById(request.getIdTipoFactor()
                                                                                         .getId())
                                                                        .orElseThrow());
            impuestoFacturaInstance.setIdTasaCuota(tasaCuotaRepository.findById(request.getIdTasaCuota()
                                                                                       .getId())
                                                                      .orElseThrow());
            impuestoFacturaInstance.setIdConceptoFactura(request.getIdConceptoFactura());

            impuestosFacturaRepository.save(impuestoFacturaInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ImpuestosFacturaService, Method: SaveOrUpdate, Error: ",
                      e);
            throw new IllegalArgumentException();
        }
    }

    public ImpuestosFacturaDto convertToDto(ImpuestosFacturaModel model) {
        return new ImpuestosFacturaDto(model.getId(),
                                       model.getDescripcion(),
                                       model.getBase(),
                                       model.getImporte(),
                                       model.getIsLocal(),
                                       model.getIsRetencion(),
                                       model.getIsPrecargado(),
                                       model.getStatus(),
                                       model.getIdImpuesto(),
                                       model.getIdTipoFactor(),
                                       model.getIdTasaCuota());
    }
}
