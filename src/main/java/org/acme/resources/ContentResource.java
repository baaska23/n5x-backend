package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Content;
import org.acme.repositories.ContentRepository;

import java.util.List;

@Path("/api/contents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContentResource {
    
    @Inject
    ContentRepository contentRepository;
    
    @GET
    public List<Content> getAll() {
        return contentRepository.listAll();
    }
}
