package org.acme.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @GeneratedValue
    @Column(name = "season_id", columnDefinition = "UUID")
    private UUID seasonId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;
    
    @Column(name = "season_number", nullable = false)
    private Integer seasonNumber;
    
    private String title;
    
    public Season() {}
    
    public UUID getSeasonId() {
        return seasonId;
    }
    
    public void setSeasonId(UUID seasonId) {
        this.seasonId = seasonId;
    }
    
    public Show getShow() {
        return show;
    }
    
    public void setShow(Show show) {
        this.show = show;
    }
    
    public Integer getSeasonNumber() {
        return seasonNumber;
    }
    
    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
