package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entities.Payment;

import java.util.UUID;

@ApplicationScoped
public class PaymenRepository implements PanacheRepositoryBase<Payment, UUID> {
}
