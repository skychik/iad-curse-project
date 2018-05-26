package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import ru.ifmo.cs.iad.iadcurseproject.entity.CommentLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.Performer;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "comment_loop", path = "comment_loop")
public interface CommentLoopRepo extends JpaRepository<CommentLoop, Long> {
	//@RestResource(exported = false)
	@Query("select count(l) from CommentLoop l where l.comment.id = :id")
	long countAllByCommentId(@Param("id") Long id);

	CommentLoop getByCommentIdAndUserId(long commentId, long userId);

	@Modifying
	@Transactional
	@Query("delete from CommentLoop l where l.id = :id")
	void removeById(@Param("id") Long id);
}
