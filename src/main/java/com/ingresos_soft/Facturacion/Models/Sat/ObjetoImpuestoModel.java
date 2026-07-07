package com.ingresos_soft.Facturacion.Models.Sat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ObjetoImpuesto")
public class ObjetoImpuestoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String clave;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Boolean status;

    public ObjetoImpuestoModel(String clave,
            String descripcion,
            Boolean status) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.status = status;
    }
}
