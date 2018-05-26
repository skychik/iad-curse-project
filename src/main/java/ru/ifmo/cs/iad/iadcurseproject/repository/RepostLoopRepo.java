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
import ru.ifmo.cs.iad.iadcurseproject.entity.RepostLoop;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "repost_loop", path = "repost_loop")
@Deprecated
public interface RepostLoopRepo extends JpaRepository<RepostLoop, Long> {
	//@RestResource(exported = false)
	@Query("select l from RepostLoop l where l.repost.id = :repostId")
	List<RepostLoop> getAllByRepostId(@Param("repostId") long repostId);

	//@RestResource(exported = false)
	@Query("select count(l) from RepostLoop l where l.repost.id = :repostId")
	long countAllByRepostId(@Param("repostId") long repostId);

	//@RestResource(exported = false)
	@Query("select l from RepostLoop l where l.repost.news.id = :newsId")
	List<RepostLoop> getAllByNewsId(@Param("newsId") long newsId);

	//@RestResource(exported = false)
	@Query("select count(l) from RepostLoop l where l.repost.news.id = :newsId")
	long countAllByNewsId(@Param("newsId") long newsId);
}
