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
@Table(name = "Menus")
public class MenusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String descripcion;

    @Column(length = 200, nullable = false)
    private String ruta;

    @Column(length = 150, nullable = false)
    private String icon;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idMenuDepende", nullable = true)
    private MenusModel idMenuDepende;

    @ManyToOne
    @JoinColumn(name = "idModule", nullable = true)
    private ModulesModel idModule;

    public MenusModel(String descripcion, String ruta, String icon, MenusModel idMenuDepende, ModulesModel idModule,
                      Boolean status) {
        this.descripcion = descripcion;
        this.ruta = ruta;
        this.icon = icon;
        this.idMenuDepende = idMenuDepende;
        this.idModule = idModule;
        this.status = status;
    }
}