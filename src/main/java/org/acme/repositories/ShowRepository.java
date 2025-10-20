package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Show;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ShowRepository implements PanacheRepositoryBase<Show, UUID> {
    public List findTop10Shows() {
        return getEntityManager()
                .createNativeQuery("select * from shows order by released_year desc limit 10", Show.class)
                .getResultList();
    }
}
