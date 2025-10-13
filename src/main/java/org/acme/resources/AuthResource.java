package org.acme.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AuthResponse;
import org.acme.dto.ErrorResponse;
import org.acme.dto.LoginRequest;
import org.acme.entities.User;
import org.acme.services.JwtService;
import org.acme.services.UserService;
import org.acme.dto.RegisterRequest;

import java.util.Optional;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    @Inject
    UserService userService;
    
    @Inject
    JwtService jwtService;
    
    @POST
    @Path("/register")
    @PermitAll
    public Response register(RegisterRequest request) {
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Username is required"))
                    .build();
        }
        
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Email is required"))
                    .build();
        }
        
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Password must be at least 6 characters"))
                    .build();
        }
        
        if (userService.usernameExists(request.getUsername())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse("Username already exists"))
                    .build();
        }
        
        if (userService.emailExists(request.getEmail())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse("Email already exists"))
                    .build();
        }
        
        try {
            User user = userService.createUser(
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword()
            );
            
            String token = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            
            AuthResponse response = new AuthResponse(
                    token,
                    refreshToken,
                    user.getUsername(),
                    user.getEmail()
            );
            
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Registration failed: " + e.getMessage()))
                    .build();
        }
    }
    
    @POST
    @Path("/login")
    @PermitAll
    public Response login(LoginRequest request) {
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Username or email is required"))
                    .build();
        }
        
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Password is required"))
                    .build();
        }
        
        Optional<User> userOpt = userService.authenticate(
                request.getUsername(),
                request.getPassword()
        );
        
        if (userOpt.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Invalid credentials"))
                    .build();
        }
        
        User user = userOpt.get();
        
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        
        AuthResponse response = new AuthResponse(
                token,
                refreshToken,
                user.getUsername(),
                user.getEmail()
        );
        
        return Response.ok(response).build();
    }
}
