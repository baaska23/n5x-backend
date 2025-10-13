package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.User;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, UUID> {

    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }
    
    public Optional<User> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
    
    public boolean existsByEmail(String email) {
        return find("email", email).count() > 0;
    }
}
