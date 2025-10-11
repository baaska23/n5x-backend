package org.acme.entities;

import jakarta.persistence.*;
import org.acme.enums.ContentType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contents")
public class Content {
    
    @Id
    @GeneratedValue
    @Column(name = "content_id", columnDefinition = "UUID")
    private UUID contentId;
    
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentType type;
    
    @OneToOne(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private Movie movie;
    
    @OneToOne(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private Show show;
    
    @ManyToMany
    @JoinTable(name = "content_genres", joinColumns = @JoinColumn(name = "content_id"))
    private List<Genre> genres = new ArrayList<>();
    
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyList> myList = new ArrayList<>();
    
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikedContent> likedContents = new ArrayList<>();
    
    public Content() {}
    
    public Content(ContentType type) {
        this.type = type;
    }
    
    public UUID getContentId() {
        return contentId;
    }
    
    public void setContentId(UUID contentId) {
        this.contentId = contentId;
    }
    
    public ContentType getType() {
        return type;
    }
    
    public void setType(ContentType type) {
        this.type = type;
    }
}
