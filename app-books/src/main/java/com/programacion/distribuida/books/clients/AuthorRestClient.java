package com.programacion.distribuida.books.clients;

import com.programacion.distribuida.books.dtos.AuthorDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@RegisterRestClient(configKey = "AuthorRestClient")
@ApplicationScoped
public interface AuthorRestClient {

    //metodo para buscar libros
    @GET
    @Path("/find/{isbn}")
    List<AuthorDto> findBookByIsbn(@PathParam("isbn") String isbn);

}
