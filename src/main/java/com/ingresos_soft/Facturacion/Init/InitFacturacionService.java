package com.ingresos_soft.Facturacion.Init;

import com.ingresos_soft.Facturacion.Models.Sat.*;
import com.ingresos_soft.Facturacion.Repositories.Sat.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Service
@Slf4j
public class InitFacturacionService {

    @Autowired
    CatalogosRepository catalogosRepository;

    @Autowired
    BancosRepository bancosRepository;

    @Autowired
    RegimenFiscalRepository regimenFiscalRepository;

    @Autowired
    MotivoCancelacionRepository motivoCancelacionRepository;

    @Autowired
    FormaPagoRepository formaPagoRepository;

    @Autowired
    MetodoPagoRepository metodoPagoRepository;

    @Autowired
    EstadosRepository estadosRepository;

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    PaisRepository paisRepository;

    @Autowired
    PeriodicidadRepository periodicidadRepository;

    @Autowired
    UsoCfdiRepository usoCfdiRepository;

    @Autowired
    TipoFactorRepository tipoFactorRepository;

    @Autowired
    TipoComprobanteRepository tipoComprobanteRepository;

    @Autowired
    ObjetoImpuestoRepository objetoImpuestoRepository;

    @Autowired
    TasaCuotaRepository tasaCuotaRepository;

    public void initCatalogos() {
        try {
            if (catalogosRepository.count() == 0) {
                catalogosRepository.save(new CatalogosModel("CG",
                                                            "Catálogos Generales",
                                                            true));
                catalogosRepository.save(new CatalogosModel("BA",
                                                            "Banco",
                                                            true));
                catalogosRepository.save(new CatalogosModel("TR",
                                                            "Tipo Régimen",
                                                            true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: InitService, Method: InitCatalogos, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initBancos() {
        try {
            if (bancosRepository.count() == 0) {
                bancosRepository.save(new BancosModel("002",
                                                      "BANAMEX",
                                                      true));
                bancosRepository.save(new BancosModel("006",
                                                      "BANCOMEXT",
                                                      true));
                bancosRepository.save(new BancosModel("009",
                                                      "BANOBRAS",
                                                      true));
                bancosRepository.save(new BancosModel("012",
                                                      "BBVA BANCOMER",
                                                      true));
                bancosRepository.save(new BancosModel("014",
                                                      "SANTANDER",
                                                      true));
                bancosRepository.save(new BancosModel("019",
                                                      "BANJERCITO",
                                                      true));
                bancosRepository.save(new BancosModel("021",
                                                      "HSBC",
                                                      true));
                bancosRepository.save(new BancosModel("030",
                                                      "BAJIO",
                                                      true));
                bancosRepository.save(new BancosModel("032",
                                                      "IXE",
                                                      true));
                bancosRepository.save(new BancosModel("036",
                                                      "INBURSA",
                                                      true));
                bancosRepository.save(new BancosModel("037",
                                                      "INTERACCIONES",
                                                      true));
                bancosRepository.save(new BancosModel("042",
                                                      "MIFEL",
                                                      true));
                bancosRepository.save(new BancosModel("044",
                                                      "SCOTIABANK",
                                                      true));
                bancosRepository.save(new BancosModel("058",
                                                      "BANREGIO",
                                                      true));
                bancosRepository.save(new BancosModel("059",
                                                      "INVEX",
                                                      true));
                bancosRepository.save(new BancosModel("060",
                                                      "BANSI",
                                                      true));
                bancosRepository.save(new BancosModel("062",
                                                      "AFIRME",
                                                      true));
                bancosRepository.save(new BancosModel("072",
                                                      "BANORTE/IXE",
                                                      true));
                bancosRepository.save(new BancosModel("102",
                                                      "THE ROYAL BANK",
                                                      true));
                bancosRepository.save(new BancosModel("103",
                                                      "AMERICAN EXPRESS",
                                                      true));
                bancosRepository.save(new BancosModel("106",
                                                      "BAMSA",
                                                      true));
                bancosRepository.save(new BancosModel("108",
                                                      "TOKYO",
                                                      true));
                bancosRepository.save(new BancosModel("110",
                                                      "JP MORGAN",
                                                      true));
                bancosRepository.save(new BancosModel("112",
                                                      "BMONEX",
                                                      true));
                bancosRepository.save(new BancosModel("113",
                                                      "VE POR MAS",
                                                      true));
                bancosRepository.save(new BancosModel("116",
                                                      "ING",
                                                      true));
                bancosRepository.save(new BancosModel("124",
                                                      "DEUTSCHE",
                                                      true));
                bancosRepository.save(new BancosModel("126",
                                                      "CREDIT SUISSE",
                                                      true));
                bancosRepository.save(new BancosModel("127",
                                                      "AZTECA",
                                                      true));
                bancosRepository.save(new BancosModel("128",
                                                      "AUTOFIN",
                                                      true));
                bancosRepository.save(new BancosModel("129",
                                                      "BARCLAYS",
                                                      true));
                bancosRepository.save(new BancosModel("130",
                                                      "COMPARTAMOS",
                                                      true));
                bancosRepository.save(new BancosModel("131",
                                                      "BANCO FAMSA",
                                                      true));
                bancosRepository.save(new BancosModel("132",
                                                      "BMULTIVA",
                                                      true));
                bancosRepository.save(new BancosModel("133",
                                                      "ACTINVER",
                                                      true));
                bancosRepository.save(new BancosModel("134",
                                                      "WAL-MART",
                                                      true));
                bancosRepository.save(new BancosModel("135",
                                                      "NAFIN",
                                                      true));
                bancosRepository.save(new BancosModel("136",
                                                      "INTERCAM BANCO",
                                                      true));
                bancosRepository.save(new BancosModel("137",
                                                      "BANCOPPEL",
                                                      true));
                bancosRepository.save(new BancosModel("138",
                                                      "ABC CAPITAL",
                                                      true));
                bancosRepository.save(new BancosModel("139",
                                                      "UBS BANK",
                                                      true));
                bancosRepository.save(new BancosModel("140",
                                                      "CONSUBANCO",
                                                      true));
                bancosRepository.save(new BancosModel("141",
                                                      "VOLKSWAGEN",
                                                      true));
                bancosRepository.save(new BancosModel("143",
                                                      "CIBANCO",
                                                      true));
                bancosRepository.save(new BancosModel("145",
                                                      "BBASE",
                                                      true));
                bancosRepository.save(new BancosModel("147",
                                                      "BANKAOOL",
                                                      true));
                bancosRepository.save(new BancosModel("148",
                                                      "PAGATODO",
                                                      true));
                bancosRepository.save(new BancosModel("149",
                                                      "FORJADORES",
                                                      true));
                bancosRepository.save(new BancosModel("150",
                                                      "INMOBILIARIO",
                                                      true));
                bancosRepository.save(new BancosModel("151",
                                                      "DONDÉ",
                                                      true));
                bancosRepository.save(new BancosModel("152",
                                                      "BANCREA",
                                                      true));
                bancosRepository.save(new BancosModel("153",
                                                      "PROGRESO",
                                                      true));
                bancosRepository.save(new BancosModel("154",
                                                      "BANCO FINTERRA",
                                                      true));
                bancosRepository.save(new BancosModel("155",
                                                      "ICBC",
                                                      true));
                bancosRepository.save(new BancosModel("156",
                                                      "SABADELL",
                                                      true));
                bancosRepository.save(new BancosModel("157",
                                                      "SHINHAN",
                                                      true));
                bancosRepository.save(new BancosModel("158",
                                                      "MIZUHO BANK",
                                                      true));
                bancosRepository.save(new BancosModel("159",
                                                      "BANK OF CHINA",
                                                      true));
                bancosRepository.save(new BancosModel("160",
                                                      "BANCO S3",
                                                      true));
                bancosRepository.save(new BancosModel("166",
                                                      "BANSEFI",
                                                      true));
                bancosRepository.save(new BancosModel("168",
                                                      "HIPOTECARIA FEDERAL",
                                                      true));
                bancosRepository.save(new BancosModel("600",
                                                      "MONEXCB",
                                                      true));
                bancosRepository.save(new BancosModel("601",
                                                      "GBM",
                                                      true));
                bancosRepository.save(new BancosModel("602",
                                                      "MASARI",
                                                      true));
                bancosRepository.save(new BancosModel("605",
                                                      "VALUE",
                                                      true));
                bancosRepository.save(new BancosModel("606",
                                                      "ESTRUCTURADORES",
                                                      true));
                bancosRepository.save(new BancosModel("607",
                                                      "TIBER",
                                                      true));
                bancosRepository.save(new BancosModel("608",
                                                      "VECTOR",
                                                      true));
                bancosRepository.save(new BancosModel("610",
                                                      "B&B",
                                                      true));
                bancosRepository.save(new BancosModel("614",
                                                      "ACCIVAL",
                                                      true));
                bancosRepository.save(new BancosModel("615",
                                                      "MERRILL LYNCH",
                                                      true));
                bancosRepository.save(new BancosModel("616",
                                                      "FINAMEX",
                                                      true));
                bancosRepository.save(new BancosModel("617",
                                                      "VALMEX",
                                                      true));
                bancosRepository.save(new BancosModel("618",
                                                      "UNICA",
                                                      true));
                bancosRepository.save(new BancosModel("619",
                                                      "MAPFRE",
                                                      true));
                bancosRepository.save(new BancosModel("620",
                                                      "PROFUTURO",
                                                      true));
                bancosRepository.save(new BancosModel("621",
                                                      "CB ACTINVER",
                                                      true));
                bancosRepository.save(new BancosModel("622",
                                                      "OACTIN",
                                                      true));
                bancosRepository.save(new BancosModel("623",
                                                      "SKANDIA",
                                                      true));
                bancosRepository.save(new BancosModel("626",
                                                      "CBDEUTSCHE",
                                                      true));
                bancosRepository.save(new BancosModel("627",
                                                      "ZURICH",
                                                      true));
                bancosRepository.save(new BancosModel("628",
                                                      "ZURICHVI",
                                                      true));
                bancosRepository.save(new BancosModel("629",
                                                      "SU CASITA",
                                                      true));
                bancosRepository.save(new BancosModel("630",
                                                      "CB INTERCAM",
                                                      true));
                bancosRepository.save(new BancosModel("631",
                                                      "CI BOLSA",
                                                      true));
                bancosRepository.save(new BancosModel("632",
                                                      "BULLTICK CB",
                                                      true));
                bancosRepository.save(new BancosModel("633",
                                                      "STERLING",
                                                      true));
                bancosRepository.save(new BancosModel("634",
                                                      "FINCOMUN",
                                                      true));
                bancosRepository.save(new BancosModel("636",
                                                      "HDI SEGUROS",
                                                      true));
                bancosRepository.save(new BancosModel("637",
                                                      "ORDER",
                                                      true));
                bancosRepository.save(new BancosModel("638",
                                                      "AKALA",
                                                      true));
                bancosRepository.save(new BancosModel("640",
                                                      "CB JPMORGAN",
                                                      true));
                bancosRepository.save(new BancosModel("642",
                                                      "REFORMA",
                                                      true));
                bancosRepository.save(new BancosModel("646",
                                                      "STP",
                                                      true));
                bancosRepository.save(new BancosModel("647",
                                                      "TELECOMM",
                                                      true));
                bancosRepository.save(new BancosModel("648",
                                                      "EVERCORE",
                                                      true));
                bancosRepository.save(new BancosModel("649",
                                                      "SKANDIA",
                                                      true));
                bancosRepository.save(new BancosModel("651",
                                                      "SEGMTY",
                                                      true));
                bancosRepository.save(new BancosModel("652",
                                                      "ASEA",
                                                      true));
                bancosRepository.save(new BancosModel("653",
                                                      "KUSPIT",
                                                      true));
                bancosRepository.save(new BancosModel("655",
                                                      "SOFIEXPRESS",
                                                      true));
                bancosRepository.save(new BancosModel("656",
                                                      "UNAGRA",
                                                      true));
                bancosRepository.save(new BancosModel("659",
                                                      "OPCIONES EMPRESARIALES DEL NOROESTE",
                                                      true));
                bancosRepository.save(new BancosModel("670",
                                                      "LIBERTAD",
                                                      true));
                bancosRepository.save(new BancosModel("901",
                                                      "CLS",
                                                      true));
                bancosRepository.save(new BancosModel("902",
                                                      "INDEVAL",
                                                      true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion , Servicio: InitFacturacionService, ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initMotivoCancelacion() {
        try {
            if (motivoCancelacionRepository.count() == 0) {
                motivoCancelacionRepository.save(new MotivoCancelacionModel("01",
                                                                            "COMPROBANTE EMITIDO CON ERRORES CON RELACION.",
                                                                            true));
                motivoCancelacionRepository.save(new MotivoCancelacionModel("02",
                                                                            "COMPROBANTE EMITIDO CON ERRORES SIN RELACION.",
                                                                            true));
                motivoCancelacionRepository.save(new MotivoCancelacionModel("03",
                                                                            "NO SE LLEVO A CABO LA OPERACION.",
                                                                            true));
                motivoCancelacionRepository.save(new MotivoCancelacionModel("04",
                                                                            "OPERACION NOMINATIVA RELACIONADA EN UNA FACTURA GLOBAL.",
                                                                            true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: InitFacturacionService, Method: InitMotivoCancelacion",
                      e);
            throw new RuntimeException();
        }
    }

    public void initFormaPago() {
        try {
            if (formaPagoRepository.count() == 0) {
                formaPagoRepository.save(new FormaPagoModel("01",
                                                            "Efectivo",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("02",
                                                            "Cheque nominativo",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("03",
                                                            "Transferencia electrónica de fondos",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("04",
                                                            "Tarjeta de crédito",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("05",
                                                            "Monedero electrónico",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("06",
                                                            "Dinero electrónico",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("08",
                                                            "Vales de despensa",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("12",
                                                            "Dación en pago",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("13",
                                                            "Pago por subrogación",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("14",
                                                            "Pago por consignación",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("15",
                                                            "Condonación",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("17",
                                                            "Compensación",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("23",
                                                            "Novación",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("24",
                                                            "Confusión",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("25",
                                                            "Remisión de deuda",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("26",
                                                            "Prescripción o caducidad",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("27",
                                                            "A satisfacción del acreedor",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("28",
                                                            "Tarjeta de débito",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("29",
                                                            "Tarjeta de servicios",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("30",
                                                            "Aplicación de anticipos",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("31",
                                                            "Intermediario pagos",
                                                            true));
                formaPagoRepository.save(new FormaPagoModel("99",
                                                            "Por definir",
                                                            true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: InitFacturacionService, Method: InitFormaPago",
                      e);
            throw new RuntimeException();
        }
    }

    public void initMetodoPago() {
        try {
            if (metodoPagoRepository.count() == 0) {
                metodoPagoRepository.save(new MetodoPagoModel("PUE",
                                                              "Pago en una sola exhibición",
                                                              true));
                metodoPagoRepository.save(new MetodoPagoModel("PPD",
                                                              "Pago en parcialidades o diferido",
                                                              true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: InitFacturacionService, Method: InitMetodoPago, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initRegimenFiscal() {
        try {
            if (regimenFiscalRepository.count() == 0) {
                regimenFiscalRepository.save(new RegimenFiscalModel("601",
                                                                    "General de Ley Personas Morales",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("603",
                                                                    "Personas Morales con Fines no Lucrativos",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("605",
                                                                    "Sueldos y Salarios e Ingresos Asimilados a Salarios",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("606",
                                                                    "Arrendamiento",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("607",
                                                                    "Régimen de Enajenación o Adquisición de Bienes",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("608",
                                                                    "Demás ingresos",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("610",
                                                                    "Residentes en el Extranjero sin Establecimiento Permanente en México",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("611",
                                                                    "Ingresos por Dividendos (socios y accionistas)",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("612",
                                                                    "Personas Físicas con Actividades Empresariales y Profesionales",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("614",
                                                                    "Ingresos por intereses",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("615",
                                                                    "Régimen de los ingresos por obtención de premios",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("616",
                                                                    "Sin obligaciones fiscales",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("620",
                                                                    "Sociedades Cooperativas de Producción que optan por diferir sus ingresos",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("621",
                                                                    "Incorporación Fiscal",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("622",
                                                                    "Actividades Agrícolas, Ganaderas, Silvícolas y Pesqueras",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("623",
                                                                    "Opcional para Grupos de Sociedades",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("624",
                                                                    "Coordinados",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("625",
                                                                    "Régimen de las Actividades Empresariales con ingresos a través de Plataformas Tecnológicas",
                                                                    true));
                regimenFiscalRepository.save(new RegimenFiscalModel("626",
                                                                    "Régimen Simplificado de Confianza",
                                                                    true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: InitFacturacionService, Method: InitRegimenSocial",
                      e);
            throw new RuntimeException();
        }
    }

    public void initPais() {
        try {
            if (paisRepository.count() == 0) {
                paisRepository.save(new PaisModel("AFG",
                                                  "Afganistán",
                                                  true));
                paisRepository.save(new PaisModel("ALA",
                                                  "Islas Åland",
                                                  true));
                paisRepository.save(new PaisModel("ALB",
                                                  "Albania",
                                                  true));
                paisRepository.save(new PaisModel("DEU",
                                                  "Alemania",
                                                  true));
                paisRepository.save(new PaisModel("AND",
                                                  "Andorra",
                                                  true));
                paisRepository.save(new PaisModel("AGO",
                                                  "Angola",
                                                  true));
                paisRepository.save(new PaisModel("AIA",
                                                  "Anguila",
                                                  true));
                paisRepository.save(new PaisModel("ATA",
                                                  "Antártida",
                                                  true));
                paisRepository.save(new PaisModel("ATG",
                                                  "Antigua y Barbuda",
                                                  true));
                paisRepository.save(new PaisModel("SAU",
                                                  "Arabia Saudita",
                                                  true));
                paisRepository.save(new PaisModel("DZA",
                                                  "Argelia",
                                                  true));
                paisRepository.save(new PaisModel("ARG",
                                                  "Argentina",
                                                  true));
                paisRepository.save(new PaisModel("ARM",
                                                  "Armenia",
                                                  true));
                paisRepository.save(new PaisModel("ABW",
                                                  "Aruba",
                                                  true));
                paisRepository.save(new PaisModel("AUS",
                                                  "Australia",
                                                  true));
                paisRepository.save(new PaisModel("AUT",
                                                  "Austria",
                                                  true));
                paisRepository.save(new PaisModel("AZE",
                                                  "Azerbaiyán",
                                                  true));
                paisRepository.save(new PaisModel("BHS",
                                                  "Bahamas (las)",
                                                  true));
                paisRepository.save(new PaisModel("BGD",
                                                  "Bangladés",
                                                  true));
                paisRepository.save(new PaisModel("BRB",
                                                  "Barbados",
                                                  true));
                paisRepository.save(new PaisModel("BHR",
                                                  "Baréin",
                                                  true));
                paisRepository.save(new PaisModel("BEL",
                                                  "Bélgica",
                                                  true));
                paisRepository.save(new PaisModel("BLZ",
                                                  "Belice",
                                                  true));
                paisRepository.save(new PaisModel("BEN",
                                                  "Benín",
                                                  true));
                paisRepository.save(new PaisModel("BMU",
                                                  "Bermudas",
                                                  true));
                paisRepository.save(new PaisModel("BLR",
                                                  "Bielorrusia",
                                                  true));
                paisRepository.save(new PaisModel("MMR",
                                                  "Myanmar",
                                                  true));
                paisRepository.save(new PaisModel("BOL",
                                                  "Bolivia, Estado Plurinacional de",
                                                  true));
                paisRepository.save(new PaisModel("BIH",
                                                  "Bosnia y Herzegovina",
                                                  true));
                paisRepository.save(new PaisModel("BWA",
                                                  "Botsuana",
                                                  true));
                paisRepository.save(new PaisModel("BRA",
                                                  "Brasil",
                                                  true));
                paisRepository.save(new PaisModel("BRN",
                                                  "Brunéi Darussalam",
                                                  true));
                paisRepository.save(new PaisModel("BGR",
                                                  "Bulgaria",
                                                  true));
                paisRepository.save(new PaisModel("BFA",
                                                  "Burkina Faso",
                                                  true));
                paisRepository.save(new PaisModel("BDI",
                                                  "Burundi",
                                                  true));
                paisRepository.save(new PaisModel("BTN",
                                                  "Bután",
                                                  true));
                paisRepository.save(new PaisModel("CPV",
                                                  "Cabo Verde",
                                                  true));
                paisRepository.save(new PaisModel("KHM",
                                                  "Camboya",
                                                  true));
                paisRepository.save(new PaisModel("CMR",
                                                  "Camerún",
                                                  true));
                paisRepository.save(new PaisModel("CAN",
                                                  "Canadá",
                                                  true));
                paisRepository.save(new PaisModel("QAT",
                                                  "Catar",
                                                  true));
                paisRepository.save(new PaisModel("BES",
                                                  "Bonaire, San Eustaquio y Saba",
                                                  true));
                paisRepository.save(new PaisModel("TCD",
                                                  "Chad",
                                                  true));
                paisRepository.save(new PaisModel("CHL",
                                                  "Chile",
                                                  true));
                paisRepository.save(new PaisModel("CHN",
                                                  "China",
                                                  true));
                paisRepository.save(new PaisModel("CYP",
                                                  "Chipre",
                                                  true));
                paisRepository.save(new PaisModel("COL",
                                                  "Colombia",
                                                  true));
                paisRepository.save(new PaisModel("COM",
                                                  "Comoras",
                                                  true));
                paisRepository.save(new PaisModel("PRK",
                                                  "Corea (la República Democrática Popular de)",
                                                  true));
                paisRepository.save(new PaisModel("KOR",
                                                  "Corea (la República de)",
                                                  true));
                paisRepository.save(new PaisModel("CIV",
                                                  "Côte d Ivoire",
                                                  true));
                paisRepository.save(new PaisModel("CRI",
                                                  "Costa Rica",
                                                  true));
                paisRepository.save(new PaisModel("HRV",
                                                  "Croacia",
                                                  true));
                paisRepository.save(new PaisModel("CUB",
                                                  "Cuba",
                                                  true));
                paisRepository.save(new PaisModel("CUW",
                                                  "Curaçao",
                                                  true));
                paisRepository.save(new PaisModel("DNK",
                                                  "Dinamarca",
                                                  true));
                paisRepository.save(new PaisModel("DMA",
                                                  "Dominica",
                                                  true));
                paisRepository.save(new PaisModel("ECU",
                                                  "Ecuador",
                                                  true));
                paisRepository.save(new PaisModel("EGY",
                                                  "Egipto",
                                                  true));
                paisRepository.save(new PaisModel("SLV",
                                                  "El Salvador",
                                                  true));
                paisRepository.save(new PaisModel("ARE",
                                                  "Emiratos Árabes Unidos (Los)",
                                                  true));
                paisRepository.save(new PaisModel("ERI",
                                                  "Eritrea",
                                                  true));
                paisRepository.save(new PaisModel("SVK",
                                                  "Eslovaquia",
                                                  true));
                paisRepository.save(new PaisModel("SVN",
                                                  "Eslovenia",
                                                  true));
                paisRepository.save(new PaisModel("ESP",
                                                  "España",
                                                  true));
                paisRepository.save(new PaisModel("USA",
                                                  "Estados Unidos (los)",
                                                  true));
                paisRepository.save(new PaisModel("EST",
                                                  "Estonia",
                                                  true));
                paisRepository.save(new PaisModel("ETH",
                                                  "Etiopía",
                                                  true));
                paisRepository.save(new PaisModel("PHL",
                                                  "Filipinas (las)",
                                                  true));
                paisRepository.save(new PaisModel("FIN",
                                                  "Finlandia",
                                                  true));
                paisRepository.save(new PaisModel("FJI",
                                                  "Fiyi",
                                                  true));
                paisRepository.save(new PaisModel("FRA",
                                                  "Francia",
                                                  true));
                paisRepository.save(new PaisModel("GAB",
                                                  "Gabón",
                                                  true));
                paisRepository.save(new PaisModel("GMB",
                                                  "Gambia (La)",
                                                  true));
                paisRepository.save(new PaisModel("GEO",
                                                  "Georgia",
                                                  true));
                paisRepository.save(new PaisModel("GHA",
                                                  "Ghana",
                                                  true));
                paisRepository.save(new PaisModel("GIB",
                                                  "Gibraltar",
                                                  true));
                paisRepository.save(new PaisModel("GRD",
                                                  "Granada",
                                                  true));
                paisRepository.save(new PaisModel("GRC",
                                                  "Grecia",
                                                  true));
                paisRepository.save(new PaisModel("GRL",
                                                  "Groenlandia",
                                                  true));
                paisRepository.save(new PaisModel("GLP",
                                                  "Guadalupe",
                                                  true));
                paisRepository.save(new PaisModel("GUM",
                                                  "Guam",
                                                  true));
                paisRepository.save(new PaisModel("GTM",
                                                  "Guatemala",
                                                  true));
                paisRepository.save(new PaisModel("GUF",
                                                  "Guayana Francesa",
                                                  true));
                paisRepository.save(new PaisModel("GGY",
                                                  "Guernsey",
                                                  true));
                paisRepository.save(new PaisModel("GIN",
                                                  "Guinea",
                                                  true));
                paisRepository.save(new PaisModel("GNB",
                                                  "Guinea-Bisáu",
                                                  true));
                paisRepository.save(new PaisModel("GNQ",
                                                  "Guinea Ecuatorial",
                                                  true));
                paisRepository.save(new PaisModel("GUY",
                                                  "Guyana",
                                                  true));
                paisRepository.save(new PaisModel("HTI",
                                                  "Haití",
                                                  true));
                paisRepository.save(new PaisModel("HND",
                                                  "Honduras",
                                                  true));
                paisRepository.save(new PaisModel("HKG",
                                                  "Hong Kong",
                                                  true));
                paisRepository.save(new PaisModel("HUN",
                                                  "Hungría",
                                                  true));
                paisRepository.save(new PaisModel("IND",
                                                  "India",
                                                  true));
                paisRepository.save(new PaisModel("IDN",
                                                  "Indonesia",
                                                  true));
                paisRepository.save(new PaisModel("IRQ",
                                                  "Irak",
                                                  true));
                paisRepository.save(new PaisModel("IRN",
                                                  "Irán (la República Islámica de)",
                                                  true));
                paisRepository.save(new PaisModel("IRL",
                                                  "Irlanda",
                                                  true));
                paisRepository.save(new PaisModel("BVT",
                                                  "Isla Bouvet",
                                                  true));
                paisRepository.save(new PaisModel("IMN",
                                                  "Isla de Man",
                                                  true));
                paisRepository.save(new PaisModel("CXR",
                                                  "Isla de Navidad",
                                                  true));
                paisRepository.save(new PaisModel("NFK",
                                                  "Isla Norfolk",
                                                  true));
                paisRepository.save(new PaisModel("ISL",
                                                  "Islandia",
                                                  true));
                paisRepository.save(new PaisModel("CYM",
                                                  "Islas Caimán (las)",
                                                  true));
                paisRepository.save(new PaisModel("CCK",
                                                  "Islas Cocos (Keeling)",
                                                  true));
                paisRepository.save(new PaisModel("COK",
                                                  "Islas Cook (las)",
                                                  true));
                paisRepository.save(new PaisModel("FRO",
                                                  "Islas Feroe (las)",
                                                  true));
                paisRepository.save(new PaisModel("SGS",
                                                  "Georgia del sur y las islas sandwich del sur",
                                                  true));
                paisRepository.save(new PaisModel("HMD",
                                                  "Isla Heard e Islas McDonald",
                                                  true));
                paisRepository.save(new PaisModel("FLK",
                                                  "Islas Malvinas [Falkland] (las)",
                                                  true));
                paisRepository.save(new PaisModel("MNP",
                                                  "Islas Marianas del Norte (las)",
                                                  true));
                paisRepository.save(new PaisModel("MHL",
                                                  "Islas Marshall (las)",
                                                  true));
                paisRepository.save(new PaisModel("PCN",
                                                  "Pitcairn",
                                                  true));
                paisRepository.save(new PaisModel("SLB",
                                                  "Islas Salomón (las)",
                                                  true));
                paisRepository.save(new PaisModel("TCA",
                                                  "Islas Turcas y Caicos (las)",
                                                  true));
                paisRepository.save(new PaisModel("UMI",
                                                  "Islas de Ultramar Menores de Estados Unidos (las)",
                                                  true));
                paisRepository.save(new PaisModel("VGB",
                                                  "Islas Vírgenes (Británicas)",
                                                  true));
                paisRepository.save(new PaisModel("VIR",
                                                  "Islas Vírgenes (EE.UU.)",
                                                  true));
                paisRepository.save(new PaisModel("ISR",
                                                  "Israel",
                                                  true));
                paisRepository.save(new PaisModel("ITA",
                                                  "Italia",
                                                  true));
                paisRepository.save(new PaisModel("JAM",
                                                  "Jamaica",
                                                  true));
                paisRepository.save(new PaisModel("JPN",
                                                  "Japón",
                                                  true));
                paisRepository.save(new PaisModel("JEY",
                                                  "Jersey",
                                                  true));
                paisRepository.save(new PaisModel("JOR",
                                                  "Jordania",
                                                  true));
                paisRepository.save(new PaisModel("KAZ",
                                                  "Kazajistán",
                                                  true));
                paisRepository.save(new PaisModel("KEN",
                                                  "Kenia",
                                                  true));
                paisRepository.save(new PaisModel("KGZ",
                                                  "Kirguistán",
                                                  true));
                paisRepository.save(new PaisModel("KIR",
                                                  "Kiribati",
                                                  true));
                paisRepository.save(new PaisModel("KWT",
                                                  "Kuwait",
                                                  true));
                paisRepository.save(new PaisModel("LAO",
                                                  "Lao, (la) República Democrática Popular",
                                                  true));
                paisRepository.save(new PaisModel("LSO",
                                                  "Lesoto",
                                                  true));
                paisRepository.save(new PaisModel("LVA",
                                                  "Letonia",
                                                  true));
                paisRepository.save(new PaisModel("LBN",
                                                  "Líbano",
                                                  true));
                paisRepository.save(new PaisModel("LBR",
                                                  "Liberia",
                                                  true));
                paisRepository.save(new PaisModel("LBY",
                                                  "Libia",
                                                  true));
                paisRepository.save(new PaisModel("LIE",
                                                  "Liechtenstein",
                                                  true));
                paisRepository.save(new PaisModel("LTU",
                                                  "Lituania",
                                                  true));
                paisRepository.save(new PaisModel("LUX",
                                                  "Luxemburgo",
                                                  true));
                paisRepository.save(new PaisModel("MAC",
                                                  "Macao",
                                                  true));
                paisRepository.save(new PaisModel("MDG",
                                                  "Madagascar",
                                                  true));
                paisRepository.save(new PaisModel("MYS",
                                                  "Malasia",
                                                  true));
                paisRepository.save(new PaisModel("MWI",
                                                  "Malaui",
                                                  true));
                paisRepository.save(new PaisModel("MDV",
                                                  "Maldivas",
                                                  true));
                paisRepository.save(new PaisModel("MLI",
                                                  "Malí",
                                                  true));
                paisRepository.save(new PaisModel("MLT",
                                                  "Malta",
                                                  true));
                paisRepository.save(new PaisModel("MAR",
                                                  "Marruecos",
                                                  true));
                paisRepository.save(new PaisModel("MTQ",
                                                  "Martinica",
                                                  true));
                paisRepository.save(new PaisModel("MUS",
                                                  "Mauricio",
                                                  true));
                paisRepository.save(new PaisModel("MRT",
                                                  "Mauritania",
                                                  true));
                paisRepository.save(new PaisModel("MYT",
                                                  "Mayotte",
                                                  true));
                paisRepository.save(new PaisModel("MEX",
                                                  "México",
                                                  true));
                paisRepository.save(new PaisModel("FSM",
                                                  "Micronesia (los Estados Federados de)",
                                                  true));
                paisRepository.save(new PaisModel("MDA",
                                                  "Moldavia (la República de)",
                                                  true));
                paisRepository.save(new PaisModel("MCO",
                                                  "Mónaco",
                                                  true));
                paisRepository.save(new PaisModel("MNG",
                                                  "Mongolia",
                                                  true));
                paisRepository.save(new PaisModel("MNE",
                                                  "Montenegro",
                                                  true));
                paisRepository.save(new PaisModel("MSR",
                                                  "Montserrat",
                                                  true));
                paisRepository.save(new PaisModel("MOZ",
                                                  "Mozambique",
                                                  true));
                paisRepository.save(new PaisModel("NAM",
                                                  "Namibia",
                                                  true));
                paisRepository.save(new PaisModel("NRU",
                                                  "Nauru",
                                                  true));
                paisRepository.save(new PaisModel("NPL",
                                                  "Nepal",
                                                  true));
                paisRepository.save(new PaisModel("NIC",
                                                  "Nicaragua",
                                                  true));
                paisRepository.save(new PaisModel("NER",
                                                  "Níger (el)",
                                                  true));
                paisRepository.save(new PaisModel("NGA",
                                                  "Nigeria",
                                                  true));
                paisRepository.save(new PaisModel("NIU",
                                                  "Niue",
                                                  true));
                paisRepository.save(new PaisModel("NOR",
                                                  "Noruega",
                                                  true));
                paisRepository.save(new PaisModel("NCL",
                                                  "Nueva Caledonia",
                                                  true));
                paisRepository.save(new PaisModel("NZL",
                                                  "Nueva Zelanda",
                                                  true));
                paisRepository.save(new PaisModel("OMN",
                                                  "Omán",
                                                  true));
                paisRepository.save(new PaisModel("NLD",
                                                  "Países Bajos (los)",
                                                  true));
                paisRepository.save(new PaisModel("PAK",
                                                  "Pakistán",
                                                  true));
                paisRepository.save(new PaisModel("PLW",
                                                  "Palaos",
                                                  true));
                paisRepository.save(new PaisModel("PSE",
                                                  "Palestina, Estado de",
                                                  true));
                paisRepository.save(new PaisModel("PAN",
                                                  "Panamá",
                                                  true));
                paisRepository.save(new PaisModel("PNG",
                                                  "Papúa Nueva Guinea",
                                                  true));
                paisRepository.save(new PaisModel("PRY",
                                                  "Paraguay",
                                                  true));
                paisRepository.save(new PaisModel("PER",
                                                  "Perú",
                                                  true));
                paisRepository.save(new PaisModel("PYF",
                                                  "Polinesia Francesa",
                                                  true));
                paisRepository.save(new PaisModel("POL",
                                                  "Polonia",
                                                  true));
                paisRepository.save(new PaisModel("PRT",
                                                  "Portugal",
                                                  true));
                paisRepository.save(new PaisModel("PRI",
                                                  "Puerto Rico",
                                                  true));
                paisRepository.save(new PaisModel("GBR",
                                                  "Reino Unido (el)",
                                                  true));
                paisRepository.save(new PaisModel("CAF",
                                                  "República Centroafricana (la)",
                                                  true));
                paisRepository.save(new PaisModel("CZE",
                                                  "República Checa (la)",
                                                  true));
                paisRepository.save(new PaisModel("MKD",
                                                  "Macedonia (la antigua República Yugoslava de)",
                                                  true));
                paisRepository.save(new PaisModel("COG",
                                                  "Congo",
                                                  true));
                paisRepository.save(new PaisModel("COD",
                                                  "Congo (la República Democrática del)",
                                                  true));
                paisRepository.save(new PaisModel("DOM",
                                                  "República Dominicana (la)",
                                                  true));
                paisRepository.save(new PaisModel("REU",
                                                  "Reunión",
                                                  true));
                paisRepository.save(new PaisModel("RWA",
                                                  "Ruanda",
                                                  true));
                paisRepository.save(new PaisModel("ROU",
                                                  "Rumania",
                                                  true));
                paisRepository.save(new PaisModel("RUS",
                                                  "Rusia, (la) Federación de",
                                                  true));
                paisRepository.save(new PaisModel("ESH",
                                                  "Sahara Occidental",
                                                  true));
                paisRepository.save(new PaisModel("WSM",
                                                  "Samoa",
                                                  true));
                paisRepository.save(new PaisModel("ASM",
                                                  "Samoa Americana",
                                                  true));
                paisRepository.save(new PaisModel("BLM",
                                                  "San Bartolomé",
                                                  true));
                paisRepository.save(new PaisModel("KNA",
                                                  "San Cristóbal y Nieves",
                                                  true));
                paisRepository.save(new PaisModel("SMR",
                                                  "San Marino",
                                                  true));
                paisRepository.save(new PaisModel("MAF",
                                                  "San Martín (parte francesa)",
                                                  true));
                paisRepository.save(new PaisModel("SPM",
                                                  "San Pedro y Miquelón",
                                                  true));
                paisRepository.save(new PaisModel("VCT",
                                                  "San Vicente y las Granadinas",
                                                  true));
                paisRepository.save(new PaisModel("SHN",
                                                  "Santa Helena, Ascensión y Tristán de Acuña",
                                                  true));
                paisRepository.save(new PaisModel("LCA",
                                                  "Santa Lucía",
                                                  true));
                paisRepository.save(new PaisModel("STP",
                                                  "Santo Tomé y Príncipe",
                                                  true));
                paisRepository.save(new PaisModel("SEN",
                                                  "Senegal",
                                                  true));
                paisRepository.save(new PaisModel("SRB",
                                                  "Serbia",
                                                  true));
                paisRepository.save(new PaisModel("SYC",
                                                  "Seychelles",
                                                  true));
                paisRepository.save(new PaisModel("SLE",
                                                  "Sierra leona",
                                                  true));
                paisRepository.save(new PaisModel("SGP",
                                                  "Singapur",
                                                  true));
                paisRepository.save(new PaisModel("SXM",
                                                  "Sint Maarten (parte holandesa)",
                                                  true));
                paisRepository.save(new PaisModel("SYR",
                                                  "Siria, (la) República Árabe",
                                                  true));
                paisRepository.save(new PaisModel("SOM",
                                                  "Somalia",
                                                  true));
                paisRepository.save(new PaisModel("LKA",
                                                  "Sri Lanka",
                                                  true));
                paisRepository.save(new PaisModel("SWZ",
                                                  "Suazilandia",
                                                  true));
                paisRepository.save(new PaisModel("ZAF",
                                                  "Sudáfrica",
                                                  true));
                paisRepository.save(new PaisModel("SDN",
                                                  "Sudán (el)",
                                                  true));
                paisRepository.save(new PaisModel("SSD",
                                                  "Sudán del Sur",
                                                  true));
                paisRepository.save(new PaisModel("SWE",
                                                  "Suecia",
                                                  true));
                paisRepository.save(new PaisModel("CHE",
                                                  "Suiza",
                                                  true));
                paisRepository.save(new PaisModel("SUR",
                                                  "Surinam",
                                                  true));
                paisRepository.save(new PaisModel("SJM",
                                                  "Svalbard y Jan Mayen",
                                                  true));
                paisRepository.save(new PaisModel("THA",
                                                  "Tailandia",
                                                  true));
                paisRepository.save(new PaisModel("TWN",
                                                  "Taiwán (Provincia de China)",
                                                  true));
                paisRepository.save(new PaisModel("TZA",
                                                  "Tanzania, República Unida de",
                                                  true));
                paisRepository.save(new PaisModel("TJK",
                                                  "Tayikistán",
                                                  true));
                paisRepository.save(new PaisModel("IOT",
                                                  "Territorio Británico del Océano Índico (el)",
                                                  true));
                paisRepository.save(new PaisModel("ATF",
                                                  "Territorios Australes Franceses (los)",
                                                  true));
                paisRepository.save(new PaisModel("TLS",
                                                  "Timor-Leste",
                                                  true));
                paisRepository.save(new PaisModel("TGO",
                                                  "Togo",
                                                  true));
                paisRepository.save(new PaisModel("TKL",
                                                  "Tokelau",
                                                  true));
                paisRepository.save(new PaisModel("TON",
                                                  "Tonga",
                                                  true));
                paisRepository.save(new PaisModel("TTO",
                                                  "Trinidad y Tobago",
                                                  true));
                paisRepository.save(new PaisModel("TUN",
                                                  "Túnez",
                                                  true));
                paisRepository.save(new PaisModel("TKM",
                                                  "Turkmenistán",
                                                  true));
                paisRepository.save(new PaisModel("TUR",
                                                  "Turquía",
                                                  true));
                paisRepository.save(new PaisModel("TUV",
                                                  "Tuvalu",
                                                  true));
                paisRepository.save(new PaisModel("UKR",
                                                  "Ucrania",
                                                  true));
                paisRepository.save(new PaisModel("UGA",
                                                  "Uganda",
                                                  true));
                paisRepository.save(new PaisModel("URY",
                                                  "Uruguay",
                                                  true));
                paisRepository.save(new PaisModel("UZB",
                                                  "Uzbekistán",
                                                  true));
                paisRepository.save(new PaisModel("VUT",
                                                  "Vanuatu",
                                                  true));
                paisRepository.save(new PaisModel("VAT",
                                                  "Santa Sede[Estado de la Ciudad del Vaticano] (la)",
                                                  true));
                paisRepository.save(new PaisModel("VEN",
                                                  "Venezuela, República Bolivariana de",
                                                  true));
                paisRepository.save(new PaisModel("VNM",
                                                  "Viet Nam",
                                                  true));
                paisRepository.save(new PaisModel("WLF",
                                                  "Wallis y Futuna",
                                                  true));
                paisRepository.save(new PaisModel("YEM",
                                                  "Yemen",
                                                  true));
                paisRepository.save(new PaisModel("DJI",
                                                  "Yibuti",
                                                  true));
                paisRepository.save(new PaisModel("ZMB",
                                                  "Zambia",
                                                  true));
                paisRepository.save(new PaisModel("ZWE",
                                                  "Zimbabue",
                                                  true));
                paisRepository.save(new PaisModel("ZZZ",
                                                  "Países no declarados",
                                                  true));

            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Service: InitFacturacionService, Method: InitPais, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initEstados() {
        try {
            if (estadosRepository.count() == 0) {
                estadosRepository.save(new EstadosModel("AGU",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Aguascalientes",
                                                        true));
                estadosRepository.save(new EstadosModel("BCN",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Baja California",
                                                        true));
                estadosRepository.save(new EstadosModel("BCS",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Baja California Sur",
                                                        true));
                estadosRepository.save(new EstadosModel("CAM",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Campeche",
                                                        true));
                estadosRepository.save(new EstadosModel("CHP",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Chiapas",
                                                        true));
                estadosRepository.save(new EstadosModel("CHH",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Chihuahua",
                                                        true));
                estadosRepository.save(new EstadosModel("COA",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Coahuila",
                                                        true));
                estadosRepository.save(new EstadosModel("COL",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Colima",
                                                        true));
                estadosRepository.save(new EstadosModel("CMX",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Ciudad de México",
                                                        true));
                estadosRepository.save(new EstadosModel("DUR",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Durango",
                                                        true));
                estadosRepository.save(new EstadosModel("GUA",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Guanajuato",
                                                        true));
                estadosRepository.save(new EstadosModel("GRO",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Guerrero",
                                                        true));
                estadosRepository.save(new EstadosModel("HID",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Hidalgo",
                                                        true));
                estadosRepository.save(new EstadosModel("JAL",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Jalisco",
                                                        true));
                estadosRepository.save(new EstadosModel("MEX",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Estado de México",
                                                        true));
                estadosRepository.save(new EstadosModel("MIC",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Michoacán",
                                                        true));
                estadosRepository.save(new EstadosModel("NLE",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Nuevo León",
                                                        true));
                estadosRepository.save(new EstadosModel("MOR",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Morelos",
                                                        true));
                estadosRepository.save(new EstadosModel("NAY",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Nayarit",
                                                        true));
                estadosRepository.save(new EstadosModel("OAX",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Oaxaca",
                                                        true));
                estadosRepository.save(new EstadosModel("PUE",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Puebla",
                                                        true));
                estadosRepository.save(new EstadosModel("QUE",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Querétaro",
                                                        true));
                estadosRepository.save(new EstadosModel("ROO",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Quintana Roo",
                                                        true));
                estadosRepository.save(new EstadosModel("SLP",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "San Luis Potosí",
                                                        true));
                estadosRepository.save(new EstadosModel("SIN",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Sinaloa",
                                                        true));
                estadosRepository.save(new EstadosModel("SON",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Sonora",
                                                        true));
                estadosRepository.save(new EstadosModel("TAB",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Tabasco",
                                                        true));
                estadosRepository.save(new EstadosModel("TAM",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Tamaulipas",
                                                        true));
                estadosRepository.save(new EstadosModel("TLA",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Tlaxcala",
                                                        true));
                estadosRepository.save(new EstadosModel("VER",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Veracruz",
                                                        true));
                estadosRepository.save(new EstadosModel("YUC",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Yucatán",
                                                        true));
                estadosRepository.save(new EstadosModel("ZAC",
                                                        paisRepository.findByClave("MEX")
                                                                      .orElseThrow(),
                                                        "Zacatecas",
                                                        true));
                estadosRepository.save(new EstadosModel("EXT",
                                                        paisRepository.findByClave("USA")
                                                                      .orElseThrow(),
                                                        "NACIDO EN EL EXTRANJERO",
                                                        true));
                estadosRepository.save(new EstadosModel("DES",
                                                        paisRepository.findByClave("ZZZ")
                                                                      .orElseThrow(),
                                                        "Estado Desconocido",
                                                        true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFactuacion, Method: InitEstados, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initEstadoCivil() {
        try {
            if (estadoCivilRepository.count() == 0) {
                estadoCivilRepository.save(new EstadoCivilModel("S",
                                                                "Soltero(a)",
                                                                true));
                estadoCivilRepository.save(new EstadoCivilModel("C",
                                                                "Casado(a)",
                                                                true));
                estadoCivilRepository.save(new EstadoCivilModel("D",
                                                                "Divorciado(a)",
                                                                true));
                estadoCivilRepository.save(new EstadoCivilModel("V",
                                                                "Viudo(a)",
                                                                true));
                estadoCivilRepository.save(new EstadoCivilModel("U",
                                                                "Unión Libre",
                                                                true));
                estadoCivilRepository.save(new EstadoCivilModel("S/D",
                                                                "No Especificado",
                                                                true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFactuacion, Method: InitEstadoCivil, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initGenero() {
        try {
            if (generoRepository.count() == 0) {
                generoRepository.save(new GeneroModel("H",
                                                      "Masculino",
                                                      true));
                generoRepository.save(new GeneroModel("M",
                                                      "Femenino",
                                                      true));
                generoRepository.save(new GeneroModel("X",
                                                      "No binario / Otro",
                                                      true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFactuacion, Method: InitGenero, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initPeriodicidad() {
        try {
            if (periodicidadRepository.count() == 0) {
                periodicidadRepository.save(new PeriodicidadModel("01",
                                                                  "Diario",
                                                                  true));
                periodicidadRepository.save(new PeriodicidadModel("02",
                                                                  "Semanal",
                                                                  true));
                periodicidadRepository.save(new PeriodicidadModel("03",
                                                                  "Quincenal",
                                                                  true));
                periodicidadRepository.save(new PeriodicidadModel("04",
                                                                  "Mensual",
                                                                  true));
                periodicidadRepository.save(new PeriodicidadModel("05",
                                                                  "Bimestral",
                                                                  true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFacturacion, Method: InitPeriodicidad, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initUsoCfdi() {
        try {
            if (usoCfdiRepository.count() == 0) {
                usoCfdiRepository.save(new UsoCfdiModel("G01",
                                                        "Adquisición de mercancías.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625,626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("G02",
                                                        "Devoluciones, descuentos o bonificaciones.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625,626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("G03",
                                                        "Gastos en general.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I01",
                                                        "Construcciones.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I02",
                                                        "Mobiliario y equipo de oficina por inversiones.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I03",
                                                        "Equipo de transporte.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I04",
                                                        "Equipo de computo y accesorios.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I05",
                                                        "Dados, troqueles, moldes, matrices y herramental.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I06",
                                                        "Comunicaciones telefónicas.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I07",
                                                        "Comunicaciones satelitales.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("I08",
                                                        "Otra maquinaria y equipo.",
                                                        "601, 603, 606, 612, 620, 621, 622, 623, 624, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D01",
                                                        "Honorarios médicos, dentales y gastos hospitalarios.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D02",
                                                        "Gastos médicos por incapacidad o discapacidad.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D03",
                                                        "Gastos funerales.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D04",
                                                        "Donativos.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D05",
                                                        "Intereses reales efectivamente pagados por créditos hipotecarios (casa habitación).",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D06",
                                                        "Aportaciones voluntarias al SAR.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D07",
                                                        "Primas por seguros de gastos médicos.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D08",
                                                        "Gastos de transportación escolar obligatoria.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D09",
                                                        "Depósitos en cuentas para el ahorro, primas que tengan como base planes de pensiones.",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("D10",
                                                        "Pagos por servicios educativos (colegiaturas).",
                                                        "605, 606, 608, 611, 612, 614, 607, 615, 625",
                                                        true,
                                                        false,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("S01",
                                                        "Sin efectos fiscales.",
                                                        "601, 603, 605, 606, 608, 610, 611, 612, 614, 616, 620, 621, 622, 623, 624, 607, 615, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("CP01",
                                                        "Pagos",
                                                        "601, 603, 605, 606, 608, 610, 611, 612, 614, 616, 620, 621, 622, 623, 624, 607, 615, 625, 626",
                                                        true,
                                                        true,
                                                        true));
                usoCfdiRepository.save(new UsoCfdiModel("CN01",
                                                        "Nómina",
                                                        "605",
                                                        true,
                                                        false,
                                                        true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFacturacion, Method: InitUsoCfdi, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initTipoFactor() {
        try {
            if (tipoFactorRepository.count() == 0) {
                tipoFactorRepository.save(new TipoFactorModel("Tasa",
                                                              true));
                tipoFactorRepository.save(new TipoFactorModel("Cuota",
                                                              true));
                tipoFactorRepository.save(new TipoFactorModel("Exento",
                                                              true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFacturacion, Method: InitTipoFactor, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initComprobante() {
        try {
            if (tipoComprobanteRepository.count() == 0) {
                tipoComprobanteRepository.save(new TipoComprobanteModel("I",
                                                                        "Ingreso",
                                                                        true));
                tipoComprobanteRepository.save(new TipoComprobanteModel("E",
                                                                        "Egreso",
                                                                        true));
                tipoComprobanteRepository.save(new TipoComprobanteModel("T",
                                                                        "Traslado",
                                                                        true));
                tipoComprobanteRepository.save(new TipoComprobanteModel("N",
                                                                        "Nómina",
                                                                        true));
                tipoComprobanteRepository.save(new TipoComprobanteModel("P",
                                                                        "Pago",
                                                                        true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFacturacion, Method: InitComprobante, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initObjetoImpuesto() {
        try {
            if (objetoImpuestoRepository.count() == 0) {
                objetoImpuestoRepository.save(new ObjetoImpuestoModel("01",
                                                                      "No objeto de impuesto.",
                                                                      true));
                objetoImpuestoRepository.save(new ObjetoImpuestoModel("02",
                                                                      "Sí objeto de impuesto.",
                                                                      true));
                objetoImpuestoRepository.save(new ObjetoImpuestoModel("03",
                                                                      "Sí objeto del impuesto y no obligado al desglose.",
                                                                      true));
                objetoImpuestoRepository.save(new ObjetoImpuestoModel("04",
                                                                      "No objeto de impuesto.",
                                                                      true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFacturacion, Method: InitObjetoImpuesto, Error: ",
                      e);
            throw new RuntimeException();
        }
    }

    public void initTasaCuota() {
        try {
            if (tasaCuotaRepository.count() == 0) {
                /* IVA */
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IVA",
                                                            "Fijo",
                                                            "No",
                                                            "Sí",
                                                            new BigDecimal("0.000000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IVA",
                                                            "Fijo",
                                                            "No",
                                                            "Sí",
                                                            new BigDecimal("0.160000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IVA",
                                                            "Rango",
                                                            "Sí",
                                                            "No",
                                                            new BigDecimal("0.160000"),
                                                            new BigDecimal("0"),
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IVA Crédito aplicado del 50%",
                                                            "Fijo",
                                                            "No",
                                                            "Sí",
                                                            new BigDecimal("0.080000"),
                                                            null,
                                                            true));
                /* IEPS */
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.265000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.300000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.530000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.500000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("1.600000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.304000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.250000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.090000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.080000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.070000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("0.060000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "No",
                                                            "Sí",
                                                            new BigDecimal("0.030000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "IEPS",
                                                            "Fijo",
                                                            "No",
                                                            "Sí",
                                                            new BigDecimal("0.000000"),
                                                            null,
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Cuota",
                                                            "IEPS",
                                                            "Rango",
                                                            "Sí",
                                                            "Sí",
                                                            new BigDecimal("59.144900"),
                                                            new BigDecimal("0"),
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "ISR",
                                                            "Rango",
                                                            "Sí",
                                                            "No",
                                                            new BigDecimal("0.350000"),
                                                            new BigDecimal("0"),
                                                            true));
                tasaCuotaRepository.save(new TasaCuotaModel("Tasa",
                                                            "ISR",
                                                            "Fijo",
                                                            "No",
                                                            "No",
                                                            new BigDecimal("0.012500"),
                                                            new BigDecimal("0"),
                                                            true));
            }
        } catch (Exception e) {
            log.error("Plugin: Facturacion, Init: InitFacturacion, Method: InitTasaCuota, Error: ",
                      e);
            throw new RuntimeException();
        }
    }
}