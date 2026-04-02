package com.ingresos_soft.Ingresos.Dto.Core;


import com.ingresos_soft.Facturacion.Models.Sat.RegimenFiscalModel;
import com.ingresos_soft.Ingresos.Models.Core.ArchivoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadesDto {
    private Long id;
    private String razonSocial;
    private String rfc;
    private String curp;
    private String codigoPostal;
    private String alias;
    private String urlPac;
    private String usuarioPac;
    private String passwordPac;
    private String numCertificado;
    private String passwordKey;
    private Boolean origenRecurso;
    private Boolean isFisica;
    private Boolean status;
    private ArchivoModel cer;
    private ArchivoModel key;
    private ArchivoModel logo;
    private ArchivoModel formato;
    private RegimenFiscalModel idRegimenFiscal;
}
