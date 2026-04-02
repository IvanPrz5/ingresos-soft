package com.ingresos_soft.Universal.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RolesMenus")
public class RolesMenusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMenu", nullable = false)
    private MenusModel idMenu;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false)
    private RolesModel idRol;

    @Column(nullable = false)
    private Boolean status;

    public RolesMenusModel(MenusModel idMenu, RolesModel idRol, Boolean status) {
        this.idMenu = idMenu;
        this.idRol = idRol;
        this.status = status;
    }
}