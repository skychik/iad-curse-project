package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsPoop;

//@RepositoryRestResource(collectionResourceRel = "news_poop", path = "news_poop")
@Repository
public interface NewsPoopRepo extends JpaRepository<NewsPoop, Long> {
	//@RestResource(exported = false)
	@Query("select p from NewsPoop p where p.news.id = :newsId ")
	Page<NewsPoop> getAllByNewsId(@Param("newsId") long newsId, Pageable pageable);

	//@RestResource(exported = false)
	@Query("select count(p) from NewsPoop p where p.news.id = :newsId")
	long countAllByNewsId(@Param("newsId") long newsId);

	NewsPoop getByNewsIdAndUserId(long newsId, long userId);

	@Modifying
	@Transactional
	@Query("delete from NewsPoop p where p.id = :id")
	void removeById(@Param("id") Long id);
}
