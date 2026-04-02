package com.ingresos_soft.Ingresos.Models.Core;

import com.ingresos_soft.Facturacion.Models.Sat.RegimenFiscalModel;

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
@Table(name = "Entidades")
public class EntidadesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false, unique = true)
    private String razonSocial;

    @Column(length = 40, nullable = false)
    private String rfc;

    @Column(length = 40, nullable = true)
    private String curp;

    @Column(length = 15, nullable = false)
    private String codigoPostal;

    @Column(length = 100, nullable = true)
    private String alias;

    @Column(length = 300, nullable = true)
    private String urlPac;

    @Column(length = 150, nullable = true)
    private String usuarioPac;

    @Column(length = 150, nullable = true)
    private String passwordPac;

    @Column(length = 200, nullable = true)
    private String numCertificado;

    @Column(length = 200, nullable = true)
    private String passwordKey;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = true)
    private Boolean origenRecurso;

    @Column(nullable = true)
    private Boolean isFisica;

    @ManyToOne
    @JoinColumn(name = "cer", nullable = true)
    private ArchivoModel cer;

    @ManyToOne
    @JoinColumn(name = "key", nullable = true)
    private ArchivoModel key;

    @ManyToOne
    @JoinColumn(name = "logo", nullable = true)
    private ArchivoModel logo;

    @ManyToOne
    @JoinColumn(name = "formato", nullable = true)
    private ArchivoModel formato;

    @ManyToOne
    @JoinColumn(name = "idRegimenFiscal", nullable = true)
    private RegimenFiscalModel idRegimenFiscal;

    public EntidadesModel(String razonSocial, String rfc, String codigoPostal, String urlPac, String usuarioPac,
                          String passwordPac, Boolean origenRecurso, Boolean isFisica, Boolean status) {
        this.razonSocial = razonSocial;
        this.rfc = rfc;
        this.codigoPostal = codigoPostal;
        this.urlPac = urlPac;
        this.usuarioPac = usuarioPac;
        this.passwordPac = passwordPac;
        this.origenRecurso = origenRecurso;
        this.isFisica = isFisica;
        this.status = status;
    }
}
