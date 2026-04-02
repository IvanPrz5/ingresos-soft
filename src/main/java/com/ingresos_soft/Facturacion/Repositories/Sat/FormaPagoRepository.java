package com.ingresos_soft.Facturacion.Repositories.Sat;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Facturacion.Models.Sat.FormaPagoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagoRepository extends JpaRepository<FormaPagoModel, Long> {
    List<FormaPagoModel> findByStatus(Boolean status);

    Optional<FormaPagoModel> findByClave(String clave);
}
