package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Payment;
import org.acme.repositories.PaymenRepository;

import java.util.List;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
    
    @Inject
    PaymenRepository paymenRepository;
    
    @GET
    public List<Payment> getAll() {
        return paymenRepository.listAll();
    }
}
