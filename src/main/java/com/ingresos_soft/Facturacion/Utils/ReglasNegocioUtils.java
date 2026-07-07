package com.ingresos_soft.Facturacion.Utils;

import java.math.BigDecimal;

public class ReglasNegocioUtils {

    public static String version() {
        return "4.0";
    }

    public static String exportacion() {
        return "01";
    }

    public static BigDecimal cantidad() {
        return BigDecimal.valueOf(1);
    }

    public static String rfcGenerico() {
        return "XAXX010101000";
    }

    public static Boolean isISR(String clave) {
        return clave.equals("002")
               ? true
               : false;
    }
}
