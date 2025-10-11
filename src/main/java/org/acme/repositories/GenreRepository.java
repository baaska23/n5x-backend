package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Genre;

import java.util.UUID;

@ApplicationScoped
public class GenreRepository implements PanacheRepositoryBase<Genre, UUID> {
}
