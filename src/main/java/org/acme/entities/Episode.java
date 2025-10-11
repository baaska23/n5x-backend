package org.acme.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @GeneratedValue
    @Column(name = "episode_id", columnDefinition = "UUID")
    private UUID episodeId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;
    
    @Column(nullable = false)
    private String title;
    
    private String duration;
    
    @Column(name = "video_url")
    private String videoUrl;
    
    @Column(name = "banner_url")
    private String bannerUrl;
    
    private String description;
    
    @Column(name = "episode_number", nullable = false)
    private Integer episodeNumber;
    
    public Episode() {}
    
    public UUID getEpisodeId() {
        return episodeId;
    }
    
    public void setEpisodeId(UUID episodeId) {
        this.episodeId = episodeId;
    }
    
    public Season getSeason() {
        return season;
    }
    
    public void setSeason(Season season) {
        this.season = season;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getVideoUrl() {
        return videoUrl;
    }
    
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    
    public String getBannerUrl() {
        return bannerUrl;
    }
    
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getEpisodeNumber() {
        return episodeNumber;
    }
    
    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}
