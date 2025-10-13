package org.acme.services;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.User;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class JwtService {
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;
    
    @ConfigProperty(name = "jwt.duration", defaultValue = "3600")
    Long duration;
    
    @ConfigProperty(name = "jwt.refresh.duration", defaultValue = "604800")
    Long refreshDuration;
    
    public String generateToken(User user) {
        return Jwt.issuer(issuer)
                .upn(user.getUsername())
                .subject(user.getUsername().toString())
                .groups(new HashSet<>(Arrays.asList("User")))
                .claim("email", user.getEmail())
                .expiresIn(duration)
                .sign();
    }
    
    public String generateRefreshToken(User user) {
        return Jwt.issuer(issuer)
                .upn(user.getUsername())
                .subject(user.getUserId().toString())
                .groups(new HashSet<>(Arrays.asList("RefreshToken")))
                .expiresIn(refreshDuration)
                .sign();
    }
}
