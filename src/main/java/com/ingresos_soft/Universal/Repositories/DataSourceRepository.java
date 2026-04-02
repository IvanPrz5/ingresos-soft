package com.ingresos_soft.Universal.Repositories;

import java.util.List;

import com.ingresos_soft.Universal.Models.DataSourceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataSourceRepository extends JpaRepository<DataSourceModel, Long> {
    List<DataSourceModel> findByStatus(Boolean status);
}