package org.acme.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "content_genres")
@IdClass(ContentGenre.class)
public class ContentGenre {
    @Id
    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
