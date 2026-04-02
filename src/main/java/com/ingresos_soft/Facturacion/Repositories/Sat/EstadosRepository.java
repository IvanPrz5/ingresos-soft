package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.EstadosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadosRepository extends JpaRepository<EstadosModel, Long> {
    List<EstadosModel> findByStatus(Boolean status);

    Optional<EstadosModel> findByClave(String clave);
}
