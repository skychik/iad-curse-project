package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;
import ru.ifmo.cs.iad.iadcurseproject.entity.Repost;

@RepositoryRestResource(collectionResourceRel = "repost", path = "repost")
public interface RepostRepository extends JpaRepository<Performer, Long> {
	@Query("select r from Repost r where r.news.id = :newsId")
	Page<Repost> getAllByNewsId(long newsId, Pageable pageable);

	@Query("select count(r) from Repost r where r.news.id = :id")
	long countAllByNewsId(long newsId);
}
