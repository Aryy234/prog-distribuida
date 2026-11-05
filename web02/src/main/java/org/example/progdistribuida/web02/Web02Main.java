package org.example.progdistribuida.web02;

import jakarta.ws.rs.SeBootstrap;

import java.util.concurrent.CompletableFuture;

public class Web02Main {
    public static void main(String[] args) {
        SeBootstrap.Configuration config = SeBootstrap.Configuration.builder()
                .host("0.0.0.0")
                .port(8080)
                .protocol("http")
                .build();

        CompletableFuture<Void> serverStopped = new CompletableFuture<>();
        SeBootstrap.start(RestApplication.class, config)
                .thenAccept(instance -> {
                    System.out.println("Servidor iniciado en: "
                            + instance.configuration().baseUri());
                    instance.stopOnShutdown(result -> serverStopped.complete(null));
                })
                .exceptionally(error -> {
                    serverStopped.completeExceptionally(error);
                    return null;
                });

        serverStopped.join();
    }
}
