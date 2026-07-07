package com.ingresos_soft.Facturacion.Repositories.Sat;

import com.ingresos_soft.Facturacion.Models.Sat.UsoCfdiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsoCfdiRepository extends JpaRepository<UsoCfdiModel, Long> {
    List<UsoCfdiModel> findByStatus(Boolean status);
}
