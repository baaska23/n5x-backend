package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Genre;
import org.acme.repositories.GenreRepository;

import java.util.List;

@Path("/api/genres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenreResource {
    
    @Inject
    GenreRepository genreRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Genre> getAll() {
        return genreRepository.listAll();
    }
}
