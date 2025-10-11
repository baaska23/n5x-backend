package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.LikedContent;
import org.acme.repositories.LikedContentRepository;

import java.util.List;

@Path("/api/liked-contents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LikedContentResource {
    
    @Inject
    LikedContentRepository likedContentRepository;
    
    @GET
    public List<LikedContent> getAll() {
        return likedContentRepository.listAll();
    }
}
