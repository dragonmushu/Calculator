package io.calculatorapi.app;

import io.calculatorapi.authentication.ServiceLoginModule;
import io.confluent.rest.RestConfig;

import java.util.Map;
import java.util.TreeMap;

public class CalculatorAppRestConfig extends RestConfig {
    private static final int PORT_DEFAULT = 8080;
    private static final String LISTENERS_DEFAULT = "http://0.0.0.0:" + PORT_DEFAULT;

    public CalculatorAppRestConfig () {
        super(baseConfigDef(PORT_DEFAULT, LISTENERS_DEFAULT), configUpdates());
    }

    private static Map<String, Object> configUpdates() {
        Map<String, Object> updates = new TreeMap();
        updates.put(AUTHENTICATION_METHOD_CONFIG, AUTHENTICATION_METHOD_BASIC);
        updates.put(AUTHENTICATION_REALM_CONFIG, ServiceLoginModule.SERVICE_REALM);
        updates.put(AUTHENTICATION_ROLES_CONFIG, java.util.List.of(ServiceLoginModule.ADMIN_ROLE));
        updates.put(AUTHENTICATION_SKIP_PATHS, "/openapi/*,/v1/calculator/evaluate/*");

        return updates;
    }
}
