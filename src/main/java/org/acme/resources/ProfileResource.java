package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Profile;
import org.acme.entities.User;
import org.acme.repositories.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Path("/api/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
    
    @Inject
    ProfileRepository profileRepository;
    
    @GET
    @RolesAllowed("User")
    public List<Profile> getAll() {
        return profileRepository.listAll();
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/{id}")
    public Profile getProfileById(@PathParam("id")UUID id) {
        return profileRepository.findById(id);
    }
    
    @POST
    @RolesAllowed("User")
    @Transactional
    public Profile createProfile(Profile request) {
        if (request == null || request.getProfileName() == null || request.getProfileName().trim().isEmpty()) {
            throw new BadRequestException("profileName is required");
        }
        
        Profile newProfile = new Profile();
        newProfile.setProfileId(UUID.randomUUID());
        newProfile.setUser(request.getUser());
        newProfile.setProfileName(request.getProfileName());
        newProfile.setAvatar(request.getAvatar());
        newProfile.setCreatedAt(LocalDateTime.now());
        
        profileRepository.persist(newProfile);
        return newProfile;
    }
    
    @PATCH
    @RolesAllowed("User")
    @Path("/{id}")
    public Profile updateProfileById(@PathParam("id") UUID id, Profile updatedProfile) {
        Profile existingProfile = profileRepository.findById(id);
        
        if (existingProfile == null) {
            throw new NotFoundException("Profile not found");
        }
        
        if (existingProfile.getProfileName() != null) {
            existingProfile.setProfileName(updatedProfile.getProfileName());
        }
        
        if (existingProfile.getAvatar() != null) {
            existingProfile.setAvatar(updatedProfile.getAvatar());
        }
        
        profileRepository.persist(existingProfile);
        return existingProfile;
    }
    
    @DELETE
    @RolesAllowed("User")
    @Path("/{id}")
    public boolean deleteProfileByUserId(@PathParam("id") UUID id) {
        return profileRepository.deleteById(id);
    }
}