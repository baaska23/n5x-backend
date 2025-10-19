package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Movie;
import org.acme.repositories.MovieRepository;

import java.util.List;
import java.util.UUID;

@Path("/api/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    
    @Inject
    MovieRepository movieRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Movie> getAll() {
        return movieRepository.listAll();
    }
    
    @GET
    @RolesAllowed("User")
    @Path("{id}")
    public Movie getMovieById(@PathParam("id")UUID id) {
        return movieRepository.findById(id);
    }
}
