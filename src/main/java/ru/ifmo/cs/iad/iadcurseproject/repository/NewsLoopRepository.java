package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;
import ru.ifmo.cs.iad.iadcurseproject.entity.RepostLoop;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "news_loop", path = "news_loop")
public interface NewsLoopRepository extends JpaRepository<Performer, Long> {
	@Query("select l from NewsLoop l where l.news.id = :newsId ")
	Page<NewsLoop> getAllByNewsId(@Param("newsId") long newsId, Pageable pageable);

	@Query("select count(l) from NewsLoop l where l.news.id = :newsId")
	long countAllByNewsId(@Param("newsId") long newsId);
}
