package com.ingresos_soft.Universal.Repositories;

import java.util.Optional;

import com.ingresos_soft.Universal.Models.MenusModel;
import com.ingresos_soft.Universal.Models.RolesMenusModel;
import com.ingresos_soft.Universal.Models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesMenusRepository extends JpaRepository<RolesMenusModel, Long> {
    Optional<RolesMenusModel> findByIdMenuAndIdRolAndStatus(MenusModel idMenu, RolesModel idRol, Boolean status);
}