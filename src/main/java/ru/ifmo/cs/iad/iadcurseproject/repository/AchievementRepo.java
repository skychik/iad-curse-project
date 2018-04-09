package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.Achievement;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

//@RepositoryRestResource(collectionResourceRel = "achievement", path = "achievement")
public interface AchievementRepo extends JpaRepository<Achievement, Long> {
}
