package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Content;

import java.util.UUID;

@ApplicationScoped
public class ContentRepository implements PanacheRepositoryBase<Content, UUID> {

}
