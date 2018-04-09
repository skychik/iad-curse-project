package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.CommentPoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

//@RepositoryRestResource(collectionResourceRel = "comment_poop", path = "comment_poop")
public interface CommentPoopRepo extends JpaRepository<CommentPoop, Long> {
	//@RestResource(exported = false)
	@Query("select count(p) from CommentPoop p where p.id = :id")
	long countAllByCommentId(@Param("id") Long id);
}
