package com.ingresos_soft.Universal.Config;

import com.ingresos_soft.Universal.Utils.DatabaseContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContext.getCurrentDatabase();
    }
}