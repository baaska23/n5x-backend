package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Subscription;

import java.util.UUID;

@ApplicationScoped
public class SubscriptionRepository implements PanacheRepositoryBase<Subscription, UUID> {
}
