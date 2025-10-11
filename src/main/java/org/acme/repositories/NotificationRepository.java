package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Notification;

import java.util.UUID;

@ApplicationScoped
public class NotificationRepository implements PanacheRepositoryBase<Notification, UUID> {
}
