package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;
import ru.ifmo.cs.iad.iadcurseproject.entity.Subscription;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "subscription", path = "subscription")
@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
}
