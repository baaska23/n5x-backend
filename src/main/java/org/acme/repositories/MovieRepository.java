package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Movie;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class MovieRepository implements PanacheRepositoryBase<Movie, UUID> {
    public List findTop10Movies() {
        return getEntityManager()
                .createNativeQuery("select * from movies order by released_year desc limit 10", Movie.class)
                .getResultList();
    }
}
