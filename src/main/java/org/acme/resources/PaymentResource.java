package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Payment;
import org.acme.repositories.PaymenRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
    
    @Inject
    PaymenRepository paymenRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Payment> getAll() {
        return paymenRepository.listAll();
    }
    
    @POST
    @RolesAllowed("User")
    @Transactional
    public Payment createPayment(Payment request) {
        if (request == null || request.getPaymentId() == null || request.getAmount() >= 0 || request.getUser() == null) {
            throw new BadRequestException("payment's all fields are required");
        }
        
        Payment newPayment = new Payment();
        newPayment.setPaymentId(UUID.randomUUID());
        newPayment.setUser(request.getUser());
        newPayment.setAmount(request.getAmount());
        newPayment.setSuccess(true);
        newPayment.setCreatedAt(LocalDateTime.now());
        
        paymenRepository.persist(newPayment);
        return newPayment;
    }
}
