package com.ingresos_soft.Facturacion.Models.Sat;

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
@Table(name = "CodigoPostal")
public class CodigoPostalModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idMunicipio")
    private MunicipiosModel idMunicipio;

    @ManyToOne
    @JoinColumn(name = "idLocalidad")
    private LocalidadModel idLocalidad;
}
