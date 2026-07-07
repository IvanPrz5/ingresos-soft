package com.ingresos_soft.Facturacion.Models.Factura;

import com.ingresos_soft.Facturacion.Models.Sat.MotivoCancelacionModel;
import com.ingresos_soft.Ingresos.Models.Core.ArchivoModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Factura")
public class FacturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uuid;

    @Column
    private LocalDateTime fechaTimbrado;

    @Column
    private LocalDateTime fechaCreacion;

    @Column(columnDefinition = "TEXT")
    private String messageErrorTimbrado;

    @Column(columnDefinition = "TEXT")
    private String messageErrorTimbradoDetail;

    @Column(columnDefinition = "TEXT")
    private String acuseCancelacion;

    @Column
    private LocalDateTime fechaCancelacion;

    @Column(name = "idUsuarioCrea")
    private Long idUsuarioCrea;

    @Column(name = "idUsuarioCancela")
    private Long idUsuarioCancela;

    @Column
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "idMotivoCancelacion")
    private MotivoCancelacionModel idMotivoCancelacion;

    @ManyToOne
    @JoinColumn(name = "idXml")
    private ArchivoModel idXml;

    @ManyToOne
    @JoinColumn(name = "idPdf")
    private ArchivoModel idPdf;

    @ManyToOne
    @JoinColumn(name = "idQr")
    private ArchivoModel idQr;

    @ManyToOne
    @JoinColumn(name = "idFacturaDetails")
    private FacturaDetails idFacturaDetails;
}
