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
@Table(name = "UsuariosDataSource")
public class UsuariosDataSourceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean status;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean seleccionada = false;

    @ManyToOne
    @JoinColumn(name = "idDataSource")
    private DataSourceModel idDataSource;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuariosModel idUsuario;

    public UsuariosDataSourceModel(DataSourceModel idDataSource, UsuariosModel idUsuario, Boolean status) {
        this.idDataSource = idDataSource;
        this.idUsuario = idUsuario;
        this.status = status;
        this.seleccionada = false;
    }
}