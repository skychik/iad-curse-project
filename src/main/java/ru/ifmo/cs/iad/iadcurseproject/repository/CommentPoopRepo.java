package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.ifmo.cs.iad.iadcurseproject.entity.CommentPoop;

//@RepositoryRestResource(collectionResourceRel = "comment_poop", path = "comment_poop")
public interface CommentPoopRepo extends JpaRepository<CommentPoop, Long> {
	//@RestResource(exported = false)
	@Query("select count(p) from CommentPoop p where p.comment.id = :id")
	long countAllByCommentId(@Param("id") Long id);

	CommentPoop getByCommentIdAndUserId(long commentId, long userId);

	@Modifying
	@Transactional
	@Query("delete from CommentPoop p where p.id = :id")
	void removeById(@Param("id") Long id);
}
