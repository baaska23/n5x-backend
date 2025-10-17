package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Season;
import org.acme.repositories.SeasonRepository;

import java.util.List;

@Path("/api/seasons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeasonResource {
    
    @Inject
    SeasonRepository seasonRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Season> getAll() {
        return seasonRepository.listAll();
    }
}
