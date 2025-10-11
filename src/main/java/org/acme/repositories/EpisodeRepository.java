package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Episode;

import java.util.UUID;

@ApplicationScoped
public class EpisodeRepository implements PanacheRepositoryBase<Episode, UUID> {
}
