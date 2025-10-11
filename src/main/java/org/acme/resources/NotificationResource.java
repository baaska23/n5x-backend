package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Notification;
import org.acme.repositories.NotificationRepository;

import java.util.List;

@Path("/api/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationResource {
    
    @Inject
    NotificationRepository notificationRepository;
    
    @GET
    public List<Notification> getAll() {
        return notificationRepository.listAll();
    }
}
