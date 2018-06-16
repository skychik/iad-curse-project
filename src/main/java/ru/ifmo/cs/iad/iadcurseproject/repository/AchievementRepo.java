package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Achievement;

//@RepositoryRestResource(collectionResourceRel = "achievement", path = "achievement")
public interface AchievementRepo extends JpaRepository<Achievement, Long> {
}
