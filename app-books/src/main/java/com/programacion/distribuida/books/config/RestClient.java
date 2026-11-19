package com.programacion.distribuida.books.config;

import com.programacion.distribuida.books.clients.AuthorRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

/*

    @Inject
    @ConfigProperty(name = "ap.authors.url")
    String authorsUrl;

    @Produces
    @ApplicationScoped
    public AuthorRestClient authorRestClient(){
        return RestClientBuilder.newBuilder()
                .baseUri(java.net.URI.create("http://localhost:8080"))
                .build(AuthorRestClient.class);
    }

 */
@ApplicationScoped
public class RestClient {



}
