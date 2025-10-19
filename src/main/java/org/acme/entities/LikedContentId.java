package org.acme.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class LikedContentId implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "profile_id", columnDefinition = "UUID", nullable = false)
    private UUID profileId;
    
    @Column(name = "content_id", columnDefinition = "UUID", nullable = false)
    private UUID contentId;
    
    public LikedContentId() {}
    
    public LikedContentId(UUID profileId, UUID contentId) {
        this.profileId = profileId;
        this.contentId = contentId;
    }
    
    public UUID getProfileId() { return profileId; }
    public void setProfileId(UUID profileId) { this.profileId = profileId; }
    
    public UUID getContentId() { return contentId; }
    public void setContentId(UUID contentId) { this.contentId = contentId; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyListId)) return false;
        LikedContentId that = (LikedContentId) o;
        return Objects.equals(profileId, that.profileId) &&
                Objects.equals(contentId, that.contentId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(profileId, contentId);
    }
}
