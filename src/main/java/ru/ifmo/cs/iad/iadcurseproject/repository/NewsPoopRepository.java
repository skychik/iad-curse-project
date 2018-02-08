package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

@RepositoryRestResource(collectionResourceRel = "news_poop", path = "news_poop")
public interface NewsPoopRepository extends JpaRepository<Performer, Long> {
}
