package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

@RepositoryRestResource(collectionResourceRel = "comment_poop", path = "comment_poop")
public interface CommentPoopRepository extends JpaRepository<Performer, Long> {
	@Query("select count(c) from Comment c where c.id = :id")
	long countAllByCommentId(@Param("id") Long id);
}
