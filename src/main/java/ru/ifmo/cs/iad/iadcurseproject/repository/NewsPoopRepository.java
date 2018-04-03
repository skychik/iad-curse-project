package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsPoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "news_poop", path = "news_poop")
public interface NewsPoopRepository extends JpaRepository<Performer, Long> {
	@Query("select p from NewsPoop p where p.news.id = :newsId ")
	Page<NewsPoop> getAllByNewsId(@Param("newsId") long newsId, Pageable pageable);

	@Query("select count(p) from NewsPoop p where p.news.id = :newsId")
	long countAllByNewsId(@Param("newsId") long newsId);
}
