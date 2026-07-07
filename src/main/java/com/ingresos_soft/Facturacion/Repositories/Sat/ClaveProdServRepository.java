package com.ingresos_soft.Facturacion.Repositories.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.ClaveProdServModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaveProdServRepository extends JpaRepository<ClaveProdServModel, Long> {
    Optional<ClaveProdServModel> findByClaveAndStatus(String clave,
            Boolean status);
}
