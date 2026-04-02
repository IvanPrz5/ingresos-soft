package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.EstadoCivilModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivilModel, Long> {
    List<EstadoCivilModel> findAllByStatus(Boolean status);

    Optional<EstadoCivilModel> findByClave(String clave);
}
