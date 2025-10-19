package org.acme.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "liked_content",
        uniqueConstraints = @UniqueConstraint(columnNames = {"profile_id", "content_id"}))
public class LikedContent {
    @EmbeddedId
    private LikedContentId id;
    
    @MapsId("profileId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
    
    @MapsId("contentId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    public LikedContent() {}
    
    public LikedContent(Profile profile, Content content) {
        this.profile = profile;
        this.content = content;
        this.id = new LikedContentId(profile.getProfileId(), content.getContentId());
    }
    
    public LikedContentId getId() { return id; }
    public void setId(LikedContentId id) { this.id = id; }
    
    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) {
        this.profile = profile;
        if (this.id == null) this.id = new LikedContentId();
        this.id.setProfileId(profile.getProfileId());
    }
    
    public Content getContent() { return content; }
    public void setContent(Content content) {
        this.content = content;
        if (this.id == null) this.id = new LikedContentId();
        this.id.setContentId(content.getContentId());
    }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
