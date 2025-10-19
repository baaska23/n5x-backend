package org.acme.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "my_list")
public class MyList {
    
    @EmbeddedId
    private MyListId id;
    
    @MapsId("profileId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
    
    @MapsId("contentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;
    
    @Column(name = "added_at")
    private LocalDateTime addedAt = LocalDateTime.now();
    
    public MyList() {}
    
    public MyList(Profile profile, Content content) {
        this.profile = profile;
        this.content = content;
        this.id = new MyListId(profile.getProfileId(), content.getContentId());
    }
    
    public MyListId getId() { return id; }
    public void setId(MyListId id) { this.id = id; }
    
    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) {
        this.profile = profile;
        if (this.id == null) this.id = new MyListId();
        this.id.setProfileId(profile.getProfileId());
    }
    
    public Content getContent() { return content; }
    public void setContent(Content content) {
        this.content = content;
        if (this.id == null) this.id = new MyListId();
        this.id.setContentId(content.getContentId());
    }
    
    public LocalDateTime getAddedAt() { return addedAt; }
    public void setAddedAt(LocalDateTime addedAt) { this.addedAt = addedAt; }
}
