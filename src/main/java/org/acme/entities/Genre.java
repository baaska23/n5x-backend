package org.acme.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue
    @Column(name = "genre_id", columnDefinition = "UUID")
    private UUID genreId;
    
    @Column(nullable = false, unique = true)
    private String title;
    
    @ManyToMany(mappedBy = "genres")
    private List<Content> contents = new ArrayList<>();
    
    public Genre() {}
    
    public UUID getGenreId() {
        return genreId;
    }
    
    public void setGenreId(UUID genreId) {
        this.genreId = genreId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
