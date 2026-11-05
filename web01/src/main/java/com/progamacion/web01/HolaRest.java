package com.progamacion.web01;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hola")
public class HolaRest {

    @GET
    public String hola() {
        return "Hola Mundo";
    }
}
