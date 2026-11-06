package org.example.programacion.distribuida.web03;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.webserver.ServerConnectionException;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class Web03Main {

    public static void handleBooks(ServerRequest req, ServerResponse res) {
        dbClient.execute()
                .createQue
    }

    public static void main(String[] args) {
        Config config = Config.create();

        var configDb = config.get("db");
        var configHttp = config.get("server");

        var dbClient = DbClient.builder()
                .config(configDb)
                .build();


        WebServer.builder()
                //realizar un configuracion externa del puerto
                .config(configHttp)
                .routing(it->it
                        .get("/books", ((req, res) ->
                                () )))
                .build()
                .start();
    }
}
