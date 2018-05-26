package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsLoop;

import javax.transaction.Transactional;

@Repository//RestResource(collectionResourceRel = "news_loop", path = "news_loop")
@Transactional
public interface NewsLoopRepo extends JpaRepository<NewsLoop, Long> {
	//@RestResource(exported = false)
	@Query("select l from NewsLoop l where l.news.id = :newsId")
	Page<NewsLoop> getAllByNewsId(@Param("newsId") long newsId, Pageable pageable);

	//@RestResource(exported = false)
	@Query("select count(l) from NewsLoop l where l.news.id = :newsId")
	long countAllByNewsId(@Param("newsId") long newsId);

	NewsLoop getByNewsIdAndUserId(long newsId, long userId);

	@Modifying
	@org.springframework.transaction.annotation.Transactional
	@Query("delete from NewsLoop l where l.id = :id")
	void removeById(@Param("id") Long id);
}
