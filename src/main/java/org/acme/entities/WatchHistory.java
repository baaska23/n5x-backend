package org.acme.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "watch_history")
public class WatchHistory {
    @Id
    @GeneratedValue
    @Column(name = "watch_id", columnDefinition = "UUID")
    private UUID watchId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episode_id")
    private Episode episode;
    
    @Column(name = "watched_seconds")
    private Integer watchedSeconds;
    
    @Column(name = "is_completed")
    private boolean isCompleted = false;
    
    @Column(name = "watched_at")
    private LocalDateTime watchedAt = LocalDateTime.now();
    
    public WatchHistory() {}
    
    public UUID getWatchId() {
        return watchId;
    }
    
    public void setWatchId(UUID watchId) {
        this.watchId = watchId;
    }
    
    public Profile getProfile() {
        return profile;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    public Episode getEpisode() {
        return episode;
    }
    
    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
    
    public Integer getWatchedSeconds() {
        return watchedSeconds;
    }
    
    public void setWatchedSeconds(Integer watchedSeconds) {
        this.watchedSeconds = watchedSeconds;
    }
    
    public boolean isCompleted() {
        return isCompleted;
    }
    
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    
    public LocalDateTime getWatchedAt() {
        return watchedAt;
    }
    
    public void setWatchedAt(LocalDateTime watchedAt) {
        this.watchedAt = watchedAt;
    }
}
