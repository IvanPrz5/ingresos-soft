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
@Table(name = "Estados")
public class EstadosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String clave;

    @Column(length = 100, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idPais")
    private PaisModel idPais;

    public EstadosModel(String clave, PaisModel idPais, String descripcion, Boolean status) {
        this.clave = clave;
        this.idPais = idPais;
        this.descripcion = descripcion;
        this.status = status;
    }
}
