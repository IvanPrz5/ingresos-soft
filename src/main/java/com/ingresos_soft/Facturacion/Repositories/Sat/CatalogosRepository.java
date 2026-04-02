package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;

import com.ingresos_soft.Facturacion.Models.Sat.CatalogosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogosRepository extends JpaRepository<CatalogosModel, Long>{
    List<CatalogosModel> findByStatus(Boolean status);
}
