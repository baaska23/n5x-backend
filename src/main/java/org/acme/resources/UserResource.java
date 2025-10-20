package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.User;
import org.acme.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    
    @Inject
    UserRepository userRepository;
    
    @GET
    @RolesAllowed("User")
    public List<User> getAll() {
        return userRepository.listAll();
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/{id}")
    public User getUserById(@PathParam("id") UUID id) {
        return userRepository.findById(id);
    }
    
    @DELETE
    @RolesAllowed("User")
    @Path("/{id}")
    @Transactional
    public boolean deleteUserById(@PathParam("id") UUID id) {
        return userRepository.deleteById(id);
    }
    
}
