package org.acme.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class MyListDto {
    private UUID profileId;
    private UUID contentId;
    private LocalDateTime addedAt;
    
    public MyListDto() {}
    
    public MyListDto(UUID profileId, UUID contentId, LocalDateTime addedAt) {
        this.profileId = profileId;
        this.contentId = contentId;
        this.addedAt = addedAt;
    }
    
    public UUID getProfileId() { return profileId; }
    public void setProfileId(UUID profileId) { this.profileId = profileId; }
    
    public UUID getContentId() { return contentId; }
    public void setContentId(UUID contentId) { this.contentId = contentId; }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}
