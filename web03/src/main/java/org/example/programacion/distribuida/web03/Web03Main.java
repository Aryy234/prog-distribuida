package org.example.programacion.distribuida.web03;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.http.Status;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class Web03Main {

    private static DbClient dbClient;
    private static final Jsonb jsonb = JsonbBuilder.create();

    public static void handleBooks(ServerRequest req, ServerResponse res) {
        List<Book> books = dbClient.execute()
                .createQuery("SELECT isbn, title FROM books")
                .execute()
                .map(row -> new Book(
                        //row.column("isbn").get(String.class),
                        //row.column("title").get(String.class)
                ))
                .toList();

        // Convertir la lista de Book a JSON usando Jsonb
        String json = jsonb.toJson(books);

        res.header("Content-Type", "application/json");
        res.send(json);
    }

    public static void createBook(ServerRequest req, ServerResponse res) {
        // Convertir el JSON del cuerpo a un objeto Book usando Jsonb
        String body = req.content().as(String.class);
        Book book = jsonb.fromJson(body, Book.class);

        //dbClient.execute()
                //.insert("INSERT INTO books (isbn, title) VALUES (?, ?)",
                        //book.getIsbn(), book.getTitle());

        //res.status(Status.CREATED_201).send(jsonb.toJson(book));
    }

    public static void createBooksBatch(ServerRequest req, ServerResponse res) {
        // Convertir un JSON a una Lista de Book usando Jsonb
        String body = req.content().as(String.class);
        List<Book> books = jsonb.fromJson(body, new ArrayList<Book>(){}.getClass().getGenericSuperclass());

        for (Book book : books) {
            //dbClient.execute()
                    //.insert("INSERT INTO books (isbn, title) VALUES (?, ?)",
                            //book.getIsbn(), book.getTitle());
        }

        res.status(Status.CREATED_201).send(jsonb.toJson(books));
    }

    public static void main(String[] args) {
        Config config = Config.create();

        var configDb = config.get("db");
        var configHttp = config.get("server");

        dbClient = DbClient.builder()
                .config(configDb)
                .build();

        var server = WebServer.builder()
                // realizar configuración externa del puerto
                .config(configHttp)
                .routing(it -> it
                        .get("/books", Web03Main::handleBooks)
                        .post("/books", Web03Main::createBook)
                        .post("/books/batch", Web03Main::createBooksBatch))
                .build()
                .start();

        System.out.println("Servidor iniciado en: http://localhost:" + server.port());
    }
}
