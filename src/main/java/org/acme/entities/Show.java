package org.acme.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue
    @Column(name = "show_id", columnDefinition = "UUID")
    private UUID showId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;
    
    @Column(nullable = false)
    private String title;
    
    private Integer duration;
    
    @Column(name = "released_year")
    private Integer releasedYear;
    
    @Column(name = "age_restriction")
    private Integer ageRestriction;
    
    private String description;
    
    @Column(name = "banner_url")
    private String bannerUrl;
    
    @Column(name = "likes_count")
    private Integer likesCount;
    
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Season> seasons = new ArrayList<>();
    
    public Show() {}
    
    public UUID getShowId() {
        return showId;
    }
    
    public void setShowId(UUID showId) {
        this.showId = showId;
    }
    
    public Content getContent() {
        return content;
    }
    
    public void setContent(Content content) {
        this.content = content;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public Integer getReleasedYear() {
        return releasedYear;
    }
    
    public void setReleasedYear(Integer releasedYear) {
        this.releasedYear = releasedYear;
    }
    
    public Integer getAgeRestriction() {
        return ageRestriction;
    }
    
    public void setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getBannerUrl() {
        return bannerUrl;
    }
    
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
    
    public Integer getLikesCount() {
        return likesCount;
    }
    
    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }
}
