package org.acme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProfileDto {
    private UUID userId;
    private UUID profileId;
    private LocalDateTime createdAt;
    
    public ProfileDto() {}
    
    public ProfileDto(UUID userId, UUID profileId, LocalDateTime createdAt) {
        this.userId = userId;
        this.profileId = profileId;
        this.createdAt = createdAt;
    }
    
    public UUID getUserId() {
        return userId;
    }
    
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    
    public UUID getProfileId() {
        return profileId;
    }
    
    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
