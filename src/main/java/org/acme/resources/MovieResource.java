package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Movie;
import org.acme.repositories.MovieRepository;

import java.util.List;

@Path("/api/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {
    
    @Inject
    MovieRepository movieRepository;
    
    @GET
    public List<Movie> getAll() {
        return movieRepository.listAll();
    }
}
