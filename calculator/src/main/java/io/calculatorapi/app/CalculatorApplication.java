package io.calculatorapi.app;

import javax.ws.rs.core.Configurable;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.calculatorapi.exceptionhandler.ServiceExceptionHandler;
import io.calculatorapi.resources.CalculatorResource;
import io.calculatorapi.resources.SwaggerResource;
import io.confluent.rest.Application;
import io.confluent.rest.validation.JacksonMessageBodyProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CalculatorApplication extends Application<CalculatorAppRestConfig> {
    private static final Logger log = LoggerFactory.getLogger(CalculatorApplication.class);

    public CalculatorApplication() {
        super(new CalculatorAppRestConfig());
    }

    @Override
    public void setupResources(Configurable<?> context, CalculatorAppRestConfig config) {
        ObjectMapper jsonMapper = new ObjectMapper();
        JacksonMessageBodyProvider jsonProvider = new JacksonMessageBodyProvider(jsonMapper);

        context.register(jsonProvider);
        context.register(new ServiceExceptionHandler());
        context.register(new CalculatorResource());
        context.register(new SwaggerResource());
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
            log.info("Started listening on port 8080...");
            app.join();
        } catch (Exception e) {
            System.exit(1);
        }
    }
}