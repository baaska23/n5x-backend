package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Season;

import java.util.UUID;

@ApplicationScoped
public class SeasonRepository implements PanacheRepositoryBase<Season, UUID> {
}
