package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Payment;
import org.acme.entities.User;
import org.acme.repositories.PaymentRepository;
import org.acme.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
    
    @Inject
    PaymentRepository paymentRepository;
    
    @Inject
    UserRepository userRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Payment> getAll() {
        return paymentRepository.listAll();
    }
    
    @POST
    @RolesAllowed("User")
    @Transactional
    public Payment createPayment(Payment request) {
        if (request == null || request.getUser() == null || request.getAmount() <= 0) {
            throw new BadRequestException("payment requires a user and a positive amount");
        }
        
        User user = userRepository.findById(request.getUser().getUserId());
        System.out.println("user " + user);
        Payment newPayment = new Payment();
        newPayment.setUser(user);
        newPayment.setAmount(request.getAmount());
        newPayment.setSuccess(true);
        newPayment.setCreatedAt(LocalDateTime.now());
         
        paymentRepository.persist(newPayment);
        return newPayment;
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/history/{id}")
    public List<Payment> getPaymentsByUser(@PathParam("id") UUID userId) {
        return paymentRepository.findByUserId(userId);
    }
}
