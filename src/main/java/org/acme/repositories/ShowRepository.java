package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Show;

import java.util.UUID;

@ApplicationScoped
public class ShowRepository implements PanacheRepositoryBase<Show, UUID> {
}
