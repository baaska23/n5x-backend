package org.acme.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.LikedContentDto;
import org.acme.dto.MyListDto;
import org.acme.entities.*;
import org.acme.repositories.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/api/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
    
    @Inject
    ProfileRepository profileRepository;
    
    @Inject
    MyListRepository myListRepository;
    
    @Inject
    ContentRepository contentRepository;
    
    @Inject
    UserRepository userRepository;
    
    @Inject
    LikedContentRepository likedContentRepository;
    
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
    @Path("/{userId}")
    @Transactional
    public Profile createProfile(@PathParam("userId") UUID userId, Profile profileInput) {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        
        Profile newProfile = new Profile();
        newProfile.setUser(user);
        newProfile.setProfileName(profileInput.getProfileName());
        newProfile.setAvatar(profileInput.getAvatar());
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
    
    @GET
    @RolesAllowed("User")
    @Path("{profileId}/my-list")
    @Transactional
    public List<MyListDto> getMyListById(@PathParam("profileId") UUID profileId) {
        List<MyList> myLists = myListRepository.listByProfileId(profileId);
        if (myLists == null || myLists.isEmpty()) {
            throw new NotFoundException("MyList not found");
        }
        return myLists.stream()
                .map(m -> {
                    UUID pid = m.getProfile() != null ? m.getProfile().getProfileId() : null;
                    UUID cid = m.getContent() != null ? m.getContent().getContentId() : null;
                    return new MyListDto(pid, cid, m.getAddedAt());
                })
                .collect(Collectors.toList());
    }
    
    @POST
    @RolesAllowed("User")
    @Path("/{profileId}/my-list")
    @Transactional
    public MyListDto addContentToMyList(@PathParam("profileId") UUID profileId, Content incomingContent) {
        Profile profile = profileRepository.findById(profileId);
        Content content = contentRepository.findById(incomingContent.getContentId());
        
        boolean exists = myListRepository.find("profile = ?1 and content = ?2", profile, content)
                .firstResultOptional()
                .isPresent();
        if (exists) {
            throw new WebApplicationException("Content already in my list", Response.Status.CONFLICT);
        }
        
        MyList myList = new MyList(profile, content);
        myList.setAddedAt(LocalDateTime.now());
        myListRepository.persist(myList);
        
        return new MyListDto(profile.getProfileId(), content.getContentId(), myList.getAddedAt());
    }
    
    @DELETE
    @RolesAllowed("User")
    @Path("/{profileId}/{contentId}/my-list")
    @Transactional
    public MyListDto removeContent(@PathParam("profileId") UUID profileId, @PathParam("contentId") UUID contentId) {
        MyList deletingContent = myListRepository.find("profile.profileId = ?1 and content.contentId = ?2", profileId, contentId)
                .firstResult();
        if (deletingContent == null) {
            throw new NotFoundException("Content not found in MyList");
        }
        myListRepository.delete(deletingContent);
        
        UUID pid = deletingContent.getProfile() != null ? deletingContent.getProfile().getProfileId() : null;
        UUID cid = deletingContent.getContent() != null ? deletingContent.getContent().getContentId() : null;
        return new MyListDto(pid, cid, deletingContent.getAddedAt());
    }
    
    @GET
    @RolesAllowed("User")
    @Path("/{profileId}/likes")
    public List<LikedContentDto> getLikedContentById(@PathParam("profileId") UUID profileId) {
        List<LikedContent> likedContents = likedContentRepository.listByProfileId(profileId);
        return likedContents.stream()
                .map(m -> {
                    UUID pid = m.getProfile().getProfileId();
                    UUID cid = m.getContent().getContentId();
                    return new LikedContentDto(pid, cid, m.getCreatedAt());
                })
                .collect(Collectors.toList());
    }
    
    @POST
    @RolesAllowed("User")
    @Path("/{profileId}/likes")
    @Transactional
    public LikedContentDto likeContent(@PathParam("profileId") UUID profileId, Content incomingContent) {
        Profile profile = profileRepository.findById(profileId);
        Content content = contentRepository.findById(incomingContent.getContentId());
        
        boolean exists = likedContentRepository.find("profile = ?1 and content = ? 2", profile, content)
                .firstResultOptional()
                .isPresent();
        if (exists) {
            throw new WebApplicationException("Content already in my list", Response.Status.CONFLICT);
        }
        
        LikedContent likedContent = new LikedContent(profile, content);
        likedContent.setCreatedAt(LocalDateTime.now());
        likedContentRepository.persist(likedContent);
        
        return new LikedContentDto(profile.getProfileId(), content.getContentId(), likedContent.getCreatedAt());
    }
    
    @DELETE
    @RolesAllowed("User")
    @Path("/{profileId}/{contentId}/likes")
    @Transactional
    public LikedContentDto removeLike(@PathParam("profileId") UUID profileId, @PathParam("contentId") UUID contentId) {
        LikedContent deletingContent = likedContentRepository.find("profile.profileId = ?1 and content.contentId = ?2", profileId, contentId)
                .firstResult();
        if (deletingContent == null) {
            throw new NotFoundException("Content not found in likes");
        }
        
        likedContentRepository.delete(deletingContent);
        UUID pid = deletingContent.getProfile().getProfileId();
        UUID cid = deletingContent.getContent().getContentId();
        return new LikedContentDto(pid, cid, deletingContent.getCreatedAt());
    }
}