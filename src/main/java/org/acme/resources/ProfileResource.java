package org.acme.resources;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.HashMap;
import java.util.Map;

@Path("/api/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class ProfileResource {
    
    @Inject
    JsonWebToken jwt;
    
    @GET
    @Path("/me")
    @RolesAllowed("User")
    public Response getCurrentUser(@Context SecurityContext ctx) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", jwt.getName());
        userInfo.put("userId", jwt.getSubject());
        userInfo.put("email", jwt.getClaim("email"));
        userInfo.put("groups", jwt.getGroups());
        
        return Response.ok(userInfo).build();
    }
    
    @GET
    @Path("/protected")
    @RolesAllowed("User")
    public Response protectedEndpoint() {
        return Response.ok("This is a protected endpoint accessible only with valid JWT").build();
    }
}