package com.ingresos_soft.Ingresos.Dto.Core;

import com.ingresos_soft.Facturacion.Models.Sat.GeneroModel;
import com.ingresos_soft.Facturacion.Models.Sat.RegimenFiscalModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ContribuyenteDto {
    private Long id;
    private String nombreCompletoOrRazonSocial;
    private LocalDate fechaNacimiento;
    private String rfc;
    private String curp;
    private Boolean personaFisica;
    private Boolean representanteLegal;
    private Boolean status;
    private GeneroModel idGenero;
    private RegimenFiscalModel idRegimenFiscal;
}
