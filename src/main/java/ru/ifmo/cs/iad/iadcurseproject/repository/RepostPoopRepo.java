package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;
import ru.ifmo.cs.iad.iadcurseproject.entity.RepostLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.RepostPoop;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "repost_poop", path = "repost_poop")
public interface RepostPoopRepo extends JpaRepository<RepostPoop, Long> {
	//@RestResource(exported = false)
	@Query("select p from RepostPoop p where p.repost.id = :repostId")
	List<RepostPoop> getAllByRepostId(@Param("repostId") long repostId);

	//@RestResource(exported = false)
	@Query("select count(p) from RepostPoop p where p.repost.id = :repostId")
	long countAllByRepostId(@Param("repostId") long repostId);

	//@RestResource(exported = false)
	@Query("select p from RepostPoop p where p.repost.news.id = :newsId")
	List<RepostPoop> getAllByNewsId(@Param("newsId") long newsId);

	//@RestResource(exported = false)
	@Query("select count(p) from RepostPoop p where p.repost.news.id = :newsId")
	long countAllByNewsId(@Param("newsId") long newsId);
}
