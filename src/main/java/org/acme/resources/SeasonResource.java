package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Episode;
import org.acme.entities.Season;
import org.acme.repositories.EpisodeRepository;
import org.acme.repositories.SeasonRepository;

import java.util.List;
import java.util.UUID;

@Path("/api/seasons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SeasonResource {
    
    @Inject
    SeasonRepository seasonRepository;
    
    @Inject
    EpisodeRepository episodeRepository;
    
    
    @GET
    @RolesAllowed("User")
    public List<Season> getAll() {
        return seasonRepository.listAll();
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/{id}/episodes")
    public List<Episode> getEpisodesBySeason(@PathParam("id")UUID id) {
        return episodeRepository.findBySeasonId(id);
    }
}
