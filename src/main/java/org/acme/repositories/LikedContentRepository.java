package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.LikedContent;

import java.util.UUID;

@ApplicationScoped
public class LikedContentRepository implements PanacheRepositoryBase<LikedContent, UUID> {
}
