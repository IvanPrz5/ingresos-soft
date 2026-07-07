package com.ingresos_soft.Facturacion.Repositories.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ObjetoImpuestoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetoImpuestoRepository extends JpaRepository<ObjetoImpuestoModel, Long> {
    List<ObjetoImpuestoModel> findByStatus(Boolean status);
}
