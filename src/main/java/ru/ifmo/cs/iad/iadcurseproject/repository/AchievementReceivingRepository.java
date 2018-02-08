package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

@RepositoryRestResource(collectionResourceRel = "achievement_receiving", path = "achievement_receiving")
public interface AchievementReceivingRepository extends JpaRepository<Performer, Long> {
}