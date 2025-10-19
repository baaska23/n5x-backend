package org.acme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class LikedContentDto {
    private UUID profileId;
    private UUID contentId;
    private LocalDateTime createdAt;
    
    public LikedContentDto() {}
    
    public LikedContentDto(UUID profileId, UUID contentId, LocalDateTime createdAt) {
        this.profileId = profileId;
        this.contentId = contentId;
        this.createdAt = createdAt;
    }
    
    public UUID getProfileId() {
        return profileId;
    }
    
    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }
    
    public UUID getContentId() {
        return contentId;
    }
    
    public void setContentId(UUID contentId) {
        this.contentId = contentId;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
