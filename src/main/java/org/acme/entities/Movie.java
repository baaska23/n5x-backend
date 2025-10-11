package org.acme.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "movie_id", columnDefinition = "UUID")
    private UUID movieId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;
    
    @Column(nullable = false)
    private String title;
    
    private Integer duration;
    
    @Column(name = "released_year")
    private Integer releasedYear;
    
    @Column(name = "age_restriction")
    private Integer ageRestriction;
    
    private String description;
    
    @Column(name = "video_url")
    private String videoUrl;
    
    @Column(name = "banner_url")
    private String bannerUrl;
    
    public Movie() {}
    
    public UUID getMovieId() {
        return movieId;
    }
    
    public void setMovieId(UUID movieId) {
        this.movieId = movieId;
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
}
