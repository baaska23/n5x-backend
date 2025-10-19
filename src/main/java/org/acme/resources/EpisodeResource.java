package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Episode;
import org.acme.repositories.EpisodeRepository;

import java.util.List;
import java.util.UUID;

@Path("/api/episodes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EpisodeResource {
    
    @Inject
    EpisodeRepository episodeRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Episode> getAll() {
        return episodeRepository.listAll();
    }
    
    @GET
    @Path("{id}")
    @RolesAllowed("User")
    public Episode getEpisodeById(@PathParam("id")UUID id) {
        return episodeRepository.findById(id);
    }
    
}
