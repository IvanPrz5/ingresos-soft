package com.ingresos_soft.Universal.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.ibm.icu.text.RuleBasedNumberFormat;

public class MoneyUtils {

    public static String toFormatMxn(String cantidad) {
        try {
            Locale locale = new Locale("es", "MX");
            NumberFormat nf = NumberFormat.getInstance(locale);
            DecimalFormat df = (DecimalFormat) nf;
            df.applyPattern("###,###,##0.00");
            return nf.format(Double.parseDouble(cantidad));
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static Double normalizarNumero(String value) {
        if (value == null || value.trim().isEmpty())
            return null;

        try {
            // 1. Eliminar espacios (incluso entre el signo y el número)
            value = value.replaceAll("\\s+", "");

            // 2. Quitar cualquier caracter no numérico válido excepto . , y -
            value = value.replaceAll("[^\\d.,-]", "");

            // 3. Usar Locale mexicano para interpretar formato
            Locale localeMX = new Locale("es", "MX");
            NumberFormat format = NumberFormat.getInstance(localeMX);
            Number number = format.parse(value);
            return number.doubleValue();
        } catch (Exception e) {
            System.err.println("No se pudo parsear el valor de Excel: '" + value + "'");
            return null;
        }
    }

    public static Double formatNumberTwoDecimals(Double imp) {
        try {
            return Math.round(imp * 100.0) / 100.0;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static BigDecimal toBigDecimal2Decimales(double valor) {
        return BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static String bigToImporteLetra(BigDecimal importe) {
        BigDecimal parteEntera = importe.setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal parteDecimal = importe.subtract(parteEntera);
        RuleBasedNumberFormat formateador = new RuleBasedNumberFormat(Locale.forLanguageTag("es"),
                                                                      RuleBasedNumberFormat.SPELLOUT);
        String textoParteEntera = formateador.format(parteEntera.intValue());
        int decimales = parteDecimal.multiply(new BigDecimal(100)).intValue();
        String textoParteDecimal = String.format("%02d", decimales);
        return textoParteEntera.toUpperCase() + " PESOS " + textoParteDecimal + "/100 M.N.";
    }
}