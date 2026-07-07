package com.ingresos_soft.Facturacion.Services.Factura;

import com.ingresos_soft.Facturacion.Dto.Factura.ConceptosFacturaDto;
import com.ingresos_soft.Facturacion.Models.Factura.ConceptosFacturaModel;
import com.ingresos_soft.Facturacion.Repositories.Factura.ConceptosFacturaRepository;
import com.ingresos_soft.Facturacion.Repositories.Sat.ObjetoImpuestoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConceptosFacturaService {

    @Autowired
    ConceptosFacturaRepository conceptosFacturaRepository;

    @Autowired
    ObjetoImpuestoRepository objetoImpuestoRepository;

    public ConceptosFacturaModel get(Long id) {
        return conceptosFacturaRepository.findById(id)
                                         .orElseThrow();
    }

    public ConceptosFacturaDto findById(Long id) {
        try {
            return this.convertToDto(this.get(id));
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ConceptosFacturaService, Method: FindById, Error: ",
                      e);
            throw new IllegalArgumentException();
        }
    }

    public List<ConceptosFacturaDto> findByIdFactura(Long idFactura) {
        try {
            return conceptosFacturaRepository.findByIdFactura_Id(idFactura)
                                             .stream()
                                             .map(this::convertToDto)
                                             .toList();
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ConceptosFacturaService, Method: FindByIdFactura, Error: ",
                      e);
            throw new IllegalArgumentException();
        }
    }

    public void saveOrUpdate(ConceptosFacturaModel request) {
        try {
            ConceptosFacturaModel conceptosFacturaInstance;

            if (request.getId() != null) {
                conceptosFacturaInstance = this.get(request.getId());
                conceptosFacturaInstance.setStatus(request.getStatus());
            } else {
                conceptosFacturaInstance = new ConceptosFacturaModel();
                conceptosFacturaInstance.setStatus(true);
            }

            conceptosFacturaInstance.setCantidad(request.getCantidad());
            conceptosFacturaInstance.setDescripcion(request.getDescripcion()
                                                           .trim());
            conceptosFacturaInstance.setValorUnitario(request.getValorUnitario());
            conceptosFacturaInstance.setImporte(request.getImporte());
            conceptosFacturaInstance.setDescuento(request.getDescuento());
            conceptosFacturaInstance.setIsPrecargado(request.getIsPrecargado());

            conceptosFacturaInstance.setIdObjetoImp(objetoImpuestoRepository.findById(request.getIdObjetoImp()
                                                                                             .getId())
                                                                            .orElseThrow());
            if (request.getIdFactura() != null) {
                conceptosFacturaInstance.setIdFactura(request.getIdFactura());
            }

            conceptosFacturaRepository.save(conceptosFacturaInstance);
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: ConceptosFacturaService, Method: SaveOrUpdate, Error: ",
                      e);
            throw new IllegalArgumentException();
        }
    }

    public ConceptosFacturaDto convertToDto(ConceptosFacturaModel request) {
        return new ConceptosFacturaDto(request.getId(),
                                       request.getCantidad(),
                                       request.getDescripcion(),
                                       request.getValorUnitario(),
                                       request.getImporte(),
                                       request.getDescuento(),
                                       request.getIsPrecargado(),
                                       request.getStatus(),
                                       request.getIdObjetoImp(),
                                       null);
    }

}
