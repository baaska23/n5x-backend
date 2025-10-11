package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Show;
import org.acme.repositories.ShowRepository;

import java.util.List;

@Path("/api/shows")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShowResource {
    
    @Inject
    ShowRepository showRepository;
    
    @GET
    public List<Show> getAll() {
        return showRepository.listAll();
    }
}
