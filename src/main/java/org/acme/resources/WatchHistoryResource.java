package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.WatchHistory;
import org.acme.repositories.WatchHistoryRepository;

@Path("/watch-history")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WatchHistoryResource {
    @Inject
    WatchHistoryRepository watchHistoryRepository;
    
    @POST
    public Response update(WatchHistory watchHistory) {
        watchHistoryRepository.saveOrUpdate(watchHistory);
        return  Response.ok().build();
    }
}
