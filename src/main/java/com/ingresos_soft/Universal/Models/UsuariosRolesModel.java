package com.ingresos_soft.Universal.Models;

import jakarta.persistence.CascadeType;
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
@Table(name = "UsuariosRoles")
public class UsuariosRolesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario", nullable = false)
    private UsuariosModel idUsuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRol", nullable = false)
    private RolesModel idRol;

    @Column(nullable = false)
    private Boolean status;

    public UsuariosRolesModel(UsuariosModel idUsuario, RolesModel idRol, Boolean status) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.status = true;
    }
}