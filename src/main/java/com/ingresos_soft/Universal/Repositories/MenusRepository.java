package com.ingresos_soft.Universal.Repositories;

import java.util.List;
import java.util.Optional;

import com.ingresos_soft.Universal.Models.MenusModel;
import com.ingresos_soft.Universal.Models.ModulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.Tuple;

public interface MenusRepository extends JpaRepository<MenusModel, Long> {
    List<MenusModel> findAllByStatus(Boolean status);

    Optional<MenusModel> findByDescripcion(String descripcion);

    List<MenusModel> findByIdModule(ModulesModel idModule);

    List<MenusModel> findByIdMenuDependeIsNull();

    List<MenusModel> findByIdMenuDepende(MenusModel idMenuDepende);

    @Query(value = "SELECT DISTINCT m.* " +
            "FROM menus m " +
            "WHERE m.status = true AND idmenudepende IS NULL;", nativeQuery = true)
    List<Tuple> findAllMenusByStatus();

    @Query(value = "SELECT DISTINCT m.* " +
            "FROM menus m " +
            "WHERE m.status = true " +
            "AND m.idmenudepende = ?1", nativeQuery = true)
    List<Tuple> findSubMenusByIdMenuDepende(Long idMenuDepende);

    @Query(value = "SELECT DISTINCT m.* " +
            "FROM menus m INNER JOIN rolesmenus rm on rm.idMenu = m.id " +
            "WHERE rm.idRol IN ?1 " +
            "AND m.status = true " +
            "AND m.idModule = ?2 " +
            "AND m.idmenudepende IS NULL", nativeQuery = true)
    List<Tuple> findMenusByRolAndModule(List<Long> listRoles, Long idModule);

    @Query(value = "SELECT DISTINCT m.* " +
            "FROM menus m INNER JOIN rolesmenus rm on rm.idMenu = m.id " +
            "WHERE rm.idRol IN ?1 " +
            "AND m.status = true " +
            "AND m.idmenudepende = ?2", nativeQuery = true)
    List<Tuple> findSubMenusByIdMenuDependeAndRolesList(List<Long> listRoles, Long idMenuDepende);

    @Query(value = "select DISTINCT m.* from menus m " +
            "INNER JOIN rolesmenus rm ON m.id = rm.idmenu " +
            "WHERE rm.idrol = ?1 AND m.idmenudepende IS NULL AND m.status = true;", nativeQuery = true)
    List<Tuple> findMenusByRole(Long idRole);

    @Query(value = "select DISTINCT m.* from menus m " +
            "INNER JOIN rolesmenus rm ON m.id = rm.idmenu " +
            "WHERE rm.idrol = ?1 AND m.idmenudepende = ?2 AND m.status = true;", nativeQuery = true)
    List<Tuple> findSubMenusByRoleAndIdMenuDepende(Long idRole, Long idMenuDepende);

    Optional<MenusModel> findByRuta(String ruta);
}