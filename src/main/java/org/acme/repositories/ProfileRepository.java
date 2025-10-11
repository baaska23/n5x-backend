package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Profile;

import java.util.UUID;

@ApplicationScoped
public class ProfileRepository implements PanacheRepositoryBase<Profile, UUID> {
}
