package com.ingresos_soft.Universal.Utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class DateUtils {

    private static final List<DateTimeFormatter> FORMATOS = Arrays.asList(
            // Formato día/mes/año (prioritario para México)
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),

            // Formatos estilo americano (con manejo especial de año)
            // DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            // DateTimeFormatter.ofPattern("M/d/yyyy"),

            // Formatos ISO y otros
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("uuuu-MM-dd"));

    public static String formatOffsetDate(LocalDate date) {
        if (date == null) {
            return "";
        }
        ZonedDateTime dateTime = date.atStartOfDay(ZoneId.of("America/Mexico_City"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "EEE MMM dd yyyy HH:mm:ss 'GMT'Z '(hora estándar central)'", Locale.ENGLISH);
        return formatter.format(dateTime);
    }

    public static String parseFecha(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía");
        }

        fechaStr = fechaStr.trim();

        // Primero intentar con los formatos estándar
        for (DateTimeFormatter formato : FORMATOS) {
            try {
                LocalDate fecha = LocalDate.parse(fechaStr, formato);
                return fecha.toString();
            } catch (DateTimeParseException e) {
                // Continuar con el siguiente formato
            }
        }

        // Si falla, intentar con lógica especial para años de 2 dígitos
        try {
            return parseFechaConYearCorto(fechaStr).toString();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    String.format("Formato de fecha no reconocido: %s. Formatos soportados:%n" +
                                          "- dd/MM/yyyy (15/04/1997)%n" +
                                          "- dd/MM/yy (15/04/97)%n" +
                                          "- MM/dd/yyyy (04/15/1997)%n" +
                                          "- M/d/yyyy (4/15/1997)%n" +
                                          "- yyyy-MM-dd (1997-04-15)", fechaStr));
        }
    }

    private static LocalDate parseFechaConYearCorto(String fechaStr) {
        String[] partes = fechaStr.split("[/-]");
        if (partes.length != 3) {
            throw new DateTimeParseException("Formato inválido", fechaStr, 0);
        }

        boolean probableDiaMes = false;
        try {
            int primerNumero = Integer.parseInt(partes[0]);
            int segundoNumero = Integer.parseInt(partes[1]);

            if (primerNumero > 12 && segundoNumero <= 12) {
                probableDiaMes = true;
            } else if (primerNumero <= 12 && segundoNumero <= 12) {
                probableDiaMes = true;
            }
        } catch (NumberFormatException e) {
            throw new DateTimeParseException("Componentes de fecha no numéricos", fechaStr, 0);
        }

        String año = partes[2];
        if (año.length() == 2) {
            int añoNum = Integer.parseInt(año);
            año = (añoNum >= 30)
                  ? "19" + año
                  : "20" + año;
        }

        String fechaNormalizada;
        if (probableDiaMes) {
            fechaNormalizada = String.format("%s/%s/%s", partes[0], partes[1], año);
            return LocalDate.parse(fechaNormalizada, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else {
            fechaNormalizada = String.format("%s/%s/%s", partes[1], partes[0], año);
            return LocalDate.parse(fechaNormalizada, DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    public static String aplicaFormatoMex(String date) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("M/d/uu");
            LocalDate fecha = LocalDate.parse(date, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return fecha.format(outputFormatter);
        } catch (Exception e) {
            throw new RuntimeException("Error al aplicar formato");
        }
    }

    // Este método ya no tiene sentido si parseas bien con parseFecha
    @Deprecated
    public static String stringToDate(String strDate) {
        throw new UnsupportedOperationException("Usa parseFecha() en su lugar.");
    }
}