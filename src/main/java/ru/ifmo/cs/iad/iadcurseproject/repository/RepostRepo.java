package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;
import ru.ifmo.cs.iad.iadcurseproject.entity.Repost;

//@RepositoryRestResource(collectionResourceRel = "repost", path = "repost")
public interface RepostRepo extends JpaRepository<Repost, Long> {
	//@RestResource(exported = false)
	@Query("select r from Repost r where r.news.id = :newsId")
	Page<Repost> getAllByNewsId(@Param("newsId") long newsId, Pageable pageable);

	//@RestResource(exported = false)
	@Query("select count(r) from Repost r where r.news.id = :newsId")
	long countAllByNewsId(@Param("newsId") long newsId);
}
