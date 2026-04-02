package com.ingresos_soft.Universal.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResultObjectResponse {
    private Boolean success;
    private String message;
    private Object data;
}