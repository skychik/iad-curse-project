package ru.ifmo.cs.iad.iadcurseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;
import ru.ifmo.cs.iad.iadcurseproject.entity.custom.NewsPreview;

import java.util.List;
import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "news", path = "news")
public interface NewsRepository extends JpaRepository<News, Long> {
	@Query(value = "SELECT u.id AS id, u.login AS authorLogin, n.content_preview AS contentPreview, " +
			"count(lop) AS loopsNumber, count(pop) AS poopsNumber, count(com) AS commentsNumber, " +
			"count(rep) AS repostsNumber, n.creation_date AS creationDate, n.altering_date AS " +
			"alteringDate " +
			"FROM public.news n, public.news_loop lop, public.news_poop pop, public.comment com , " +
			"public.repost rep, public.user u " +
			"WHERE u.id = n.id_user AND lop.id_news = n.id AND pop.id_news = n.id AND " +
			"com.id_news = n.id AND n.id IN ( " +
			"SELECT rep.id_news " +
			"FROM public.repost rep, public.subscription sub " +
			"WHERE rep.id_user = sub.id_on_whom " +
			")" +
			"GROUP BY u.id, u.login, n.content_preview, n.creation_date, n.altering_date " +
			"ORDER BY n.altering_date", nativeQuery = true)
	List<NewsPreview> findAllNewsPreviews();

	//@Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
	//  User findByLastnameOrFirstname(@Param("lastname") String lastname,
	//                                 @Param("firstname") String firstname);
}
