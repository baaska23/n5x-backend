package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Subscription;
import org.acme.entities.User;
import org.acme.repositories.SubscriptionRepository;
import org.acme.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionResource {
    
    @Inject
    SubscriptionRepository subscriptionRepository;
    @Inject
    UserRepository userRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Subscription> getAll() {
        return subscriptionRepository.listAll();
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/{userId}/history")
    public List<Subscription> getAllByUserId(@PathParam("userId") UUID userId) {
        List<Subscription> subscriptions = subscriptionRepository.listByUserId(userId);
        if (subscriptions == null || subscriptions.isEmpty()) {
            throw new NotFoundException("No subscriptions found for userId: " + userId);
        }
        return subscriptions;
    }
    
    @POST
    @RolesAllowed("User")
    @Transactional
    public Subscription createSubscription(Subscription request) {
        if (request.getUser() == null || request.getUser().getUserId() == null || request.getDueAt() == null) {
            throw new BadRequestException("userId and dueAt are required");
        }
        
        User user = userRepository.findById(request.getUser().getUserId());
        if (user == null) {
            throw new BadRequestException("User not found");
        }
        
        Subscription newSubscription = new Subscription();
        newSubscription.setUser(user);
        newSubscription.setDueAt(request.getDueAt());
        newSubscription.setCreatedAt(LocalDateTime.now());
        
        subscriptionRepository.persist(newSubscription);
        return newSubscription;
    }
}
