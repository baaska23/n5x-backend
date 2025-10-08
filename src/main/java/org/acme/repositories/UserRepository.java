package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

}
