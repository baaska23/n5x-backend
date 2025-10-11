package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.MyList;

import java.util.UUID;

@ApplicationScoped
public class MyListRepository implements PanacheRepositoryBase<MyList, UUID> {
}
