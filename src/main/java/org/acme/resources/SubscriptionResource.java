package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Subscription;
import org.acme.repositories.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionResource {
    
    @Inject
    SubscriptionRepository subscriptionRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Subscription> getAll() {
        return subscriptionRepository.listAll();
    }
    
    @POST
    @RolesAllowed("User")
    @Transactional
    public Subscription createSubscription(Subscription request) {
        if (request.getSubscriptionId() == null || request.getUser() == null || request.getDueAt() == null) {
            throw new BadRequestException("subscription's all fields are required");
        }
        
        Subscription newSubscription = new Subscription();
        newSubscription.setSubscriptionId(UUID.randomUUID());
        newSubscription.setUser(request.getUser());
        newSubscription.setDueAt(request.getDueAt());
        newSubscription.setCreatedAt(LocalDateTime.now());
        
        subscriptionRepository.persist(newSubscription);
        return newSubscription;
    }
}
