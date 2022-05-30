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


public class CalculatorApplication extends Application<RestConfig> {
    public CalculatorApplication() {
        super(new RestConfig(RestConfig.baseConfigDef().define(RestConfig.AUTHENTICATION_METHOD_CONFIG, RestConfig.AUTHENTICATION_METHOD_BASIC))., "/v1/calculator");
    }

    @Override
    public void setupResources(Configurable<?> context, RestConfig config) {

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
            CalculatorApplication app = new CalculatorApplication();
            app.start();
            app.join();
        } catch (RestConfigException e) {
            System.exit(1);
        } catch (Exception e) {
        }
    }

}