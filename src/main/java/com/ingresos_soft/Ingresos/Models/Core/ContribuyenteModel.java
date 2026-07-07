package com.ingresos_soft.Ingresos.Models.Core;


import com.ingresos_soft.Facturacion.Models.Sat.GeneroModel;
import com.ingresos_soft.Facturacion.Models.Sat.RegimenFiscalModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Contribuyente")
public class ContribuyenteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String primerApellido;

    @Column
    private String segundoApellido;

    @Column
    private LocalDate fechaNacimiento;

    @Column
    private String razonSocial;

    @Column(nullable = false, length = 25)
    private String rfc;

    @Column(length = 30)
    private String curp;

    @Column(length = 10)
    private String codigoPostal;

    @Column(nullable = false)
    private Boolean personaFisica;

    @Column(nullable = false)
    private Boolean representanteLegal;

    @Column(nullable = false)
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    private GeneroModel idGenero;

    @ManyToOne
    @JoinColumn(name = "idRegimenFiscal", nullable = false)
    private RegimenFiscalModel idRegimenFiscal;

    public String getNombreCompleto() {
        return Stream.of(this.getNombre(),
                         this.getPrimerApellido(),
                         this.getSegundoApellido())
                     .filter(s -> s != null && !s.isBlank())
                     .collect(Collectors.joining(" "));
    }
}
