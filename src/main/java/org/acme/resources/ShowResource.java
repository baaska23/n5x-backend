package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Show;
import org.acme.entities.Season;
import org.acme.repositories.SeasonRepository;
import org.acme.repositories.ShowRepository;

import java.util.List;
import java.util.UUID;

@Path("/api/shows")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShowResource {
    
    @Inject
    ShowRepository showRepository;
    
    @Inject
    SeasonRepository seasonRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Show> getAll() {
        return showRepository.listAll();
    }
    
    @GET
    @RolesAllowed("User")
    @Path("{id}")
    public Show getShowById(@PathParam("id")UUID id) {
        return showRepository.findById(id);
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/{id}/seasons")
    public List<Season> getSeasonsByShowId(@PathParam("id") UUID id) {
        return seasonRepository.findByShowId(id);
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/trending")
    public List getTrendingShows() {
        return showRepository.findTop10Shows();
    }
}
