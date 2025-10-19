package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.MyList;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class MyListRepository implements PanacheRepositoryBase<MyList, UUID> {
    public MyList findByProfileId(UUID profileId) {
        return find("profile.profileId = ?1", profileId).firstResult();
    }
    
    public List<MyList> listByProfileId(UUID profileId) {
        return list("profile.profileId = ?1", profileId);
    }
}
