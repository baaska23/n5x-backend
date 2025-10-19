package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Season;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SeasonRepository implements PanacheRepositoryBase<Season, UUID> {
    public List<Season> findByShowId(UUID showId) {
        return find("show.showId = ?1", showId).list();
    }
}