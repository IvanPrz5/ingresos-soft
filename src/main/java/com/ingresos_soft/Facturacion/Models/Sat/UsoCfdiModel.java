package com.ingresos_soft.Facturacion.Models.Sat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "UsoCfdi")
public class UsoCfdiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String clave;

    @Column(length = 250, nullable = false)
    private String descripcion;

    @Column(length = 250, nullable = false)
    private String regimenFiscalReceptor;

    @Column(nullable = false)
    private Boolean fisica;

    @Column(nullable = false)
    private Boolean moral;

    @Column(nullable = false)
    private Boolean status;

    public UsoCfdiModel(String clave,
                        String descripcion,
                        String regimenFiscalReceptor,
                        Boolean fisica,
                        Boolean moral,
                        Boolean status) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.regimenFiscalReceptor = regimenFiscalReceptor;
        this.fisica = fisica;
        this.moral = moral;
        this.status = status;
    }
}
