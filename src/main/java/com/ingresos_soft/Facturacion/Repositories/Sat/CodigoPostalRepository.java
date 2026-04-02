package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.CodigoPostalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodigoPostalRepository extends JpaRepository<CodigoPostalModel, Long> {
    Optional<CodigoPostalModel> findByCodigo(String codigo);
}
