package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Subscription;
import org.acme.repositories.SubscriptionRepository;

import java.util.List;

@Path("/api/subscriptions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubscriptionResource {
    
    @Inject
    SubscriptionRepository subscriptionRepository;
    
    @GET
    public List<Subscription> getAll() {
        return subscriptionRepository.listAll();
    }
}
