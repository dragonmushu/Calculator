package io.calculatorapi.app;


import javax.ws.rs.core.Configurable;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.calculatorapi.resources.CalculatorResource;
import io.confluent.rest.Application;
import io.confluent.rest.RestConfig;
import io.confluent.rest.RestConfigException;
import io.confluent.rest.validation.JacksonMessageBodyProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import java.io.File;

public class CalculatorApplication extends Application<CalculatorAppRestConfig> {
    public static final String BASE_API_PATH = "/v1/calculator";

    public CalculatorApplication() {
        super(new CalculatorAppRestConfig(), BASE_API_PATH);
    }

    @Override
    public void setupResources(Configurable<?> context, CalculatorAppRestConfig config) {
        ObjectMapper jsonMapper = new ObjectMapper();
        JacksonMessageBodyProvider jsonProvider = new JacksonMessageBodyProvider(jsonMapper);

        context.register(jsonProvider);
        context.register(ApiListingResource.class);
        context.register(SwaggerSerializers.class);
        context.register(new CalculatorResource());

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/v1/calculator");
        beanConfig.setScan(true);

    }

    public static void main(String[] args) {
        try {

            // configure jaas config file
            File file = new File("src/main/resources/jaas.conf");
            String path = file.getAbsolutePath();
            System.setProperty("java.security.auth.login.config", path);

            // start application
            CalculatorApplication app = new CalculatorApplication();
            app.start();
            app.join();
        } catch (Exception e) {
            System.exit(1);
        }
    }
}