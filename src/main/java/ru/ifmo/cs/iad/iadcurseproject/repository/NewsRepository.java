package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;

@RepositoryRestResource(collectionResourceRel = "news", path = "news")
public interface NewsRepository extends JpaRepository<News, Long> {
}
