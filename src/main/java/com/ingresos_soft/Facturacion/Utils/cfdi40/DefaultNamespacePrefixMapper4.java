package com.ingresos_soft.Facturacion.Utils.cfdi40;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

import java.util.HashMap;
import java.util.Map;

public class DefaultNamespacePrefixMapper4 extends NamespacePrefixMapper {

    private final Map<String, String> namespaceMap = new HashMap<>();

    public DefaultNamespacePrefixMapper4() {
        namespaceMap.put("http://www.sat.gob.mx/nomina12", "nomina12");
        namespaceMap.put("http://www.sat.gob.mx/cfd/4", "cfdi");
        namespaceMap.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
    }

    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        return namespaceMap.getOrDefault(namespaceUri, suggestion);
    }
}