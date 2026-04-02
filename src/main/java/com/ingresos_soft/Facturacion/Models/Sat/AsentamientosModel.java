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
@Table(name = "Asentamientos")
public class AsentamientosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String clave;

    @Column(length = 200, nullable = false)
    private String nombre;

    @Column(length = 150, nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idCodigoPostal")
    private CodigoPostalModel idCodigoPostal;
}
