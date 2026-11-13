package com.programacion.distribuida.authors.rest;

import com.programacion.distribuida.authors.db.Author;
import com.programacion.distribuida.authors.repo.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class AuthorRest {

    final AuthorRepository authorRepository;

    @Inject
    public AuthorRest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GET
    public List<Author> getAllAuthors() {
        return authorRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") Integer id) {
        return authorRepository.findByIdOptional(id)
                .map(author -> Response.ok(author).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
