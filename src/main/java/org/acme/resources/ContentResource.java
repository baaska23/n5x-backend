package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Content;
import org.acme.repositories.ContentRepository;

import java.util.List;
import java.util.UUID;

@Path("/api/contents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContentResource {
    
    @Inject
    ContentRepository contentRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Content> getAll() {
        return contentRepository.listAll();
    }
    
    @GET
    @RolesAllowed("User")
    @Path("{id}")
    public Content findById(@PathParam("id")UUID id) {
        return contentRepository.findById(id);
    }
}
