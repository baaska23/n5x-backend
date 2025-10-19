package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Notification;
import org.acme.repositories.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationResource {
    
    @Inject
    NotificationRepository notificationRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Notification> getAll() {
        return notificationRepository.listAll();
    }
    
    //admin sending notification to users
    @POST
    @RolesAllowed("Admin")
    @Transactional
    public Notification createNotification(Notification request) {
        if (request == null || request.getUser() == null || request.getMessage() == null) {
            throw new BadRequestException("notification's all fields are required");
        }
        
        Notification newNotification = new Notification();
        newNotification.setUser(request.getUser());
        newNotification.setMessage(request.getMessage());
        newNotification.setCreatedAt(LocalDateTime.now());
        
        notificationRepository.persist(newNotification);
        return newNotification;
    }
    
    @PATCH
    @RolesAllowed("User")
    @Path("/mark-all-read")
    @Transactional
    public List<Notification> markAsReadAll() {
        List<Notification> notifications = notificationRepository.listAll();
        
        for (Notification n : notifications) {
            n.setRead(true);
        }
        
        return notifications;
    }
    
    @PATCH
    @RolesAllowed("User")
    @Path("/{id}/read")
    @Transactional
    public Notification markAsRead(@PathParam("id") UUID id) {
        Notification notification = notificationRepository.findById(id);
        notification.setRead(true);
        return notification;
    }
}
