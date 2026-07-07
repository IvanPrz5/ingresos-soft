package com.ingresos_soft.Ingresos.Repositories.Core;

import com.ingresos_soft.Ingresos.Models.Core.ContribuyenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContribuyenteRepository extends JpaRepository<ContribuyenteModel, Long> {
    List<ContribuyenteModel> findByStatusOrderById(Boolean status);
}
