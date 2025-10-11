package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Profile;
import org.acme.repositories.ProfileRepository;

import java.util.List;

@Path("/api/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
    
    @Inject
    ProfileRepository profileRepository;
    
    @GET
    public List<Profile> getAll() {
        return profileRepository.listAll();
    }
}
