package com.programacion.distribuida.authors.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@Path("/config")
public class TestConfigRest {

        @Inject
        @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
        Integer port;

        @Inject
        @ConfigProperty(name = "app.name")
        String appName;

        @GET
        public String test() {

                //Punto de acceso al sistema de configuracion
                Config config = ConfigProvider.getConfig();

                //Listar fuentes de configuracion
                config.getConfigSources().forEach(configSource -> {
                        System.out.printf("[%5d] %s%n", configSource.getOrdinal(), configSource.getName());

                });

                //recuperar un valor de configuracion
                String value = config.getValue("app.name", String.class);
                Optional<String> appTitle = config.getOptionalValue("app.title", String.class);

                System.out.println("Value of 'app.name': " + value);
                System.out.println("Value of 'quarkus.http.port': " + port);
                System.out.println("Value of 'app.name' (injected): " + appName);

                if(appTitle.isPresent()) {
                    System.out.println("Value of 'app.title': " + appTitle.get());
                } else {
                    System.out.println("Value of 'app.title' not found");
                }

                return value;
        }


}
