package com.programacion.distribuida.books.clients;

import com.programacion.distribuida.books.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public interface AuthorRestClient {

    //metodo para buscar libros
    @GET
    @Path("/find/{isbn}")
    Book findBookByIsbn(@PathParam("isbn") String isbn);

}
