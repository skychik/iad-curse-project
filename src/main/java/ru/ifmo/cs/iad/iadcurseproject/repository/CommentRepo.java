package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.Comment;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
	@Query("select c from Comment c where c.news.id = :newsId")
	List<Comment> getAllForNewsId(@Param("newsId") long newsId, Pageable pagination);

	//@RestResource(exported = false)
	@Query("select count(c) from Comment c where c.news.id = :id")
	long countAllByNewsId(@Param("id") Long id);

	void deleteById(Long id);

	Comment getByIdAndNewsId(long id, long newsId);
}
