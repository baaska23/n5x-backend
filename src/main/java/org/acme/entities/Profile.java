package org.acme.entities;

import jakarta.persistence.*;
import jakarta.json.bind.annotation.JsonbTransient;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "profile_id", columnDefinition = "UUID")
    private UUID profileId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "profile_name", nullable = false)
    private String profileName;
    
    private String avatar;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MyList> myList = new ArrayList<>();
    
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikedContent> likedContents = new ArrayList<>();
    
    public Profile() {}
    
    public UUID getProfileId() {
        return profileId;
    }
    
    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public String getProfileName() {
        return profileName;
    }
    
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @JsonbTransient
    public List<MyList> getMyList() {
        return myList;
    }
    
    public void setMyList(List<MyList> myList) {
        this.myList = myList;
    }
    
    @JsonbTransient
    public List<LikedContent> getLikedContents() {
        return likedContents;
    }
    
    public void setLikedContents(List<LikedContent> likedContents) {
        this.likedContents = likedContents;
    }
}
