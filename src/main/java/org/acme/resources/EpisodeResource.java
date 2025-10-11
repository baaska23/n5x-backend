package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Episode;
import org.acme.repositories.EpisodeRepository;

import java.util.List;

@Path("/api/episodes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EpisodeResource {
    
    @Inject
    EpisodeRepository episodeRepository;
    
    @GET
    public List<Episode> getAll() {
        return episodeRepository.listAll();
    }
}
