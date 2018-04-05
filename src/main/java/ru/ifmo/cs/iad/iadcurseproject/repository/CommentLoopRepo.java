package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.CommentLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "comment_loop", path = "comment_loop")
public interface CommentLoopRepo extends JpaRepository<Performer, Long> {
	//@RestResource(exported = false)
	@Query("select count(c) from Comment c where c.id = :id")
	long countAllByCommentId(@Param("id") Long id);
}
