package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsLoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.NewsPoop;
import ru.ifmo.cs.iad.iadcurseproject.entity.Repost;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.data.domain.PageRequest.of;


@RestController
public class NewsController {
	@Autowired
	private NewsRepository newsRepository;
	@Autowired
	private RepostRepository repostRepository;
	@Autowired
	private NewsLoopRepository newsLoopRepository;
	@Autowired
	private NewsPoopRepository newsPoopRepository;
	@Autowired
	private RepostLoopRepository repostLoopRepository;
	@Autowired
	private RepostPoopRepository repostPoopRepository;

	@PersistenceContext
	private EntityManager em; // TODO: IS NULL

	private Logger logger = LoggerFactory.getLogger("application");

//	@Override
//	public Iterable getAllNews(int userId, int fromNewsId, int total) {
//		logger.info((em == null) + "");
//		Query query = em.createNativeQuery("" +
//						"SELECT u.login AS authorLogin, n.content_preview AS contentPreview, " +
//						"count(lop) AS loopsNumber, count(pop) AS poopsNumber, count(com) AS commentsNumber, " +
//						"count(rep) AS repostsNumber, n.creation_date AS creation_date, n.altering_date AS " +
//						"altering_date " +
//						"FROM public.news n, public.news_loop lop, public.news_poop pop, public.comment com , " +
//						"public.repost rep, public.user u " +
//						"WHERE u.id = n.id_user AND lop.id_news = n.id AND pop.id_news = n.id AND " +
//						"com.id_news = n.id AND n.id IN ( " +
//						"SELECT rep.id_news " +
//						"FROM public.repost rep, public.subscription sub " +
//						"WHERE rep.id_user = sub.id_on_whom " +
//						")" +
//						"GROUP BY u.login, n.content_preview, n.creation_date, n.altering_date " +
//						"ORDER BY n.altering_date", NewsPreview.class
//				/*"SELECT u.login AS authorLogin, n.content_preview AS contentPreview, count(lop) AS loopsNumber, " +
//				"count(pop) AS poopsNumber, count(com) AS commentsNumber, count(rep) AS repostsNumber " +
//				"FROM public.news n, public.news_loop lop, public.news_poop pop, public.comment com , " +
//				"public.repost rep, public.user u " +
//				"WHERE u.id = n.id_user AND lop.id_news = n.id AND pop.id_news = n.id AND " +
//				"com.id_news = n.id AND n.id IN (" +
//				"SELECT rep.id_news " +
//				"FROM public.repost rep, public.subscription sub " +
//				"WHERE rep.id_user = sub.id_on_whom " +
//				"GROUP BY u.login " +
//				"ORDER BY n.altering_date" +
//				")"*/);

//		if (query == null) return null;
//
//		List list = newsRepository.findAllNewsPreviews();
//		ArrayList<NewsPreview> previews = new ArrayList<>();
//		for (Object preview : list) {
//			Object[] p = (Object[]) preview;
//			NewsPreview preview1 = new NewsPreview() {
//				@Override
//				public Long getId() {
//					return (long) p[0];
//				}
//				@Override
//				public String getAuthorLogin() {
//					return (String) p[1];
//				}
//
//				@Override
//				public String getContentPreview() {
//					return (long) p[0];
//				}
//
//				@Override
//				public Long getLoopsNumber() {
//					return (long) p[0];
//				}
//
//				@Override
//				public Long getPoopsNumber() {
//					return (long) p[0];
//				}
//
//				@Override
//				public Long getCommentsNumber() {
//					return (long) p[0];
//				}
//
//				@Override
//				public Long getRepostsNumber() {
//					return (long) p[0];
//				}
//
//				@Override
//				public Long getCreationDate() {
//					return (long) p[0];
//				}
//
//				@Override
//				public Long getAlteringDate() {
//					return (long) p[0];
//				}
//			};
//		}
//
//		// max - 20 news
//		if (total <= 20) {
//			return list.subList(0, total - 1);
//		} else {
//			return list.subList(0, 19);
//		}

	// TODO: write this in one SQL request
//		/*List<News> newsList;
//		List<Repost> repostList = new LinkedList<>();
//		if (userId == -1) {     //TODO: remove
//			newsList = newsRepository.findAll();
//			for (News news : newsList) {
//				repostList.addAll(news.getReposts());
//			}
//		} else {
//			User user = userRepository.findOne(Long.valueOf(userId));
//			Set<Subscription> subscriptions = user.getSubscriptions();
//			newsList = new LinkedList<>();
//			for (Subscription subscription : subscriptions) {
//				newsList.addAll(subscription.getOnWhom().getNewsSet());
//				repostList.addAll(subscription.getOnWhom().getReposts());
//			}
//		}
//
//		List<Event> events = new LinkedList<>();
//		events.addAll(newsList);
//		events.addAll(repostList);
//		events.sort((Event e1, Event e2) -> {
//			Timestamp date1 = e1.getAlteringDate() == null ? e1.getCreationDate() : e1.getAlteringDate();
//			Timestamp date2 = e2.getAlteringDate() == null ? e2.getCreationDate() : e2.getAlteringDate();
//			return date1.compareTo(date2);
//		});
////		for (News news : allNews) {
////			if (news.getContent().length() > 120) {
////				news.setContent(news.getContent().substring(0, 119) + "...");
////			}
////		}
//		events = events.subList(fromNewsId, toNewsId + 1);
//		List<Event> response = new LinkedList<>();
//		for(Event event : events) {
//			if (event.getClass() == News.class) { // TODO: ??????????????????????????????????????????????????????
//				News news = (News) event;
//				News obj = new News();
//				obj.setContent(news.getContent());
//				obj.setAuthor(news.getAuthor());
//				response.add(obj);
//			}
//		}
//		return response;*/
//	//}

	@GetMapping("/news")
	public Page<News> getNewsForUserIdWithPageNumberAndPageSize(
			@RequestParam(value = "userId") long userId,
			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		return newsRepository.getAllForUserId(userId, of(pageNumber, pageSize));
	}

	@GetMapping("/news/{newsId}")
	public News getNewsById(@PathVariable("newsId") long newsId) {
		return newsRepository.getOne(newsId);
	}

	@GetMapping("/news/{newsId}/reposts")
	public Page<Repost> getRepostsByNewsId(
			@PathVariable("newsId") long newsId,
			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		return repostRepository.getAllByNewsId(newsId, of(pageNumber, pageSize));
	}

	@GetMapping("/news/{newsId}/reposts_number")
	public long getRepostsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return repostRepository.countAllByNewsId(newsId);
	}

	@GetMapping("/news/{newsId}/loops")
	public Page<NewsLoop> getLoopsByNewsId(
			@PathVariable("newsId") long newsId,
			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		return newsLoopRepository.getAllByNewsId(newsId, of(pageNumber, pageSize));
	}

	@GetMapping("/news/{newsId}/total_loops_number")
	public long getTotalLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsLoopRepository.countAllByNewsId(newsId) + repostLoopRepository.countAllByNewsId(newsId);
	}

	@GetMapping("/news/{newsId}/loops_number")
	public long getLoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsLoopRepository.countAllByNewsId(newsId);
	}

	@GetMapping("/news/{newsId}/poops")
	public Page<NewsPoop> getPoopsByNewsId(
			@PathVariable("newsId") long newsId,
			@RequestParam(value = "size", required = false, defaultValue = "15") int pageSize,
			@RequestParam(value = "page", required = false, defaultValue = "0") int pageNumber) {
		return newsPoopRepository.getAllByNewsId(newsId, of(pageNumber, pageSize));
	}

	@GetMapping("/news/{newsId}/total_poops_number")
	public long getTotalPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsPoopRepository.countAllByNewsId(newsId) + repostPoopRepository.countAllByNewsId(newsId);
	}

	@GetMapping("/news/{newsId}/poops_number")
	public long getPoopsNumberByNewsId(@PathVariable("newsId") long newsId) {
		return newsPoopRepository.countAllByNewsId(newsId);
	}

	public int likeNewsByNewsId() {
		return 0;
	}
}
