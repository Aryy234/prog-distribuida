package org.example.programacion.distribuida.web03;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.webserver.WebServer;

public class Web03Main {

    static void main(String[] args) {
        Config config = Config.create();

        var configDb = config.get("db");
        var configHttp = config.get("server");

        var dbClient = DbClient.builder()
                .config(configDb)
                .build();


        WebServer.builder()
                //realizar un configuracion externa del puerto
                .config(configHttp)
                .build()
                .start();
    }
}
