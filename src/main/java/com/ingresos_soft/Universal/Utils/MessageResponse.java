package com.ingresos_soft.Universal.Utils;

import java.util.HashMap;
import java.util.Map;

public class MessageResponse {
    public static ResultObjectResponse success(Object data) {
        return new ResultObjectResponse(true, "Transacción Exitosa", data);
    }

    public static ResultObjectResponse error(Object data, String mensaje) {
        Map<String, Object> response = new HashMap<>();
        response.put("messageError", mensaje);
        response.put("data", data);
        return new ResultObjectResponse(false, "Transacción Erronea", response);
    }
}
