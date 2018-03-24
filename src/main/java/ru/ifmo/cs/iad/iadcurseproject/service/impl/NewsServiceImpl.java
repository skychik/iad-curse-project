package ru.ifmo.cs.iad.iadcurseproject.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ifmo.cs.iad.iadcurseproject.entity.*;
import ru.ifmo.cs.iad.iadcurseproject.entity.custom.NewsPreview;
import ru.ifmo.cs.iad.iadcurseproject.repository.NewsRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.SubscriptionRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.UserRepository;
import ru.ifmo.cs.iad.iadcurseproject.service.NewsService;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

@Service
public class NewsServiceImpl implements NewsService {
	private final NewsRepository newsRepository;
	private final UserRepository userRepository;
	private final SubscriptionRepository subscriptionRepository;

	@PersistenceContext
	private EntityManager em; // TODO: IS NULL

	private Logger logger = LoggerFactory.getLogger("application");

	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository, UserRepository userRepository,
	                       SubscriptionRepository subscriptionRepository) {
		this.newsRepository = newsRepository;
		this.userRepository = userRepository;
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public Iterable getAllNews(int userId, int fromNewsId, int total) {
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

		List list = newsRepository.findAllNewsPreviews();

		// max - 20 news
		if (total <= 20) {
			return list.subList(0, total - 1);
		} else {
			return list.subList(0, 19);
		}

		// TODO: write this in one SQL request
		/*List<News> newsList;
		List<Repost> repostList = new LinkedList<>();
		if (userId == -1) {     //TODO: remove
			newsList = newsRepository.findAll();
			for (News news : newsList) {
				repostList.addAll(news.getReposts());
			}
		} else {
			User user = userRepository.findOne(Long.valueOf(userId));
			Set<Subscription> subscriptions = user.getSubscriptions();
			newsList = new LinkedList<>();
			for (Subscription subscription : subscriptions) {
				newsList.addAll(subscription.getOnWhom().getNewsSet());
				repostList.addAll(subscription.getOnWhom().getReposts());
			}
		}

		List<Event> events = new LinkedList<>();
		events.addAll(newsList);
		events.addAll(repostList);
		events.sort((Event e1, Event e2) -> {
			Timestamp date1 = e1.getAlteringDate() == null ? e1.getCreationDate() : e1.getAlteringDate();
			Timestamp date2 = e2.getAlteringDate() == null ? e2.getCreationDate() : e2.getAlteringDate();
			return date1.compareTo(date2);
		});
//		for (News news : allNews) {
//			if (news.getContent().length() > 120) {
//				news.setContent(news.getContent().substring(0, 119) + "...");
//			}
//		}
		events = events.subList(fromNewsId, toNewsId + 1);
		List<Event> response = new LinkedList<>();
		for(Event event : events) {
			if (event.getClass() == News.class) { // TODO: ??????????????????????????????????????????????????????
				News news = (News) event;
				News obj = new News();
				obj.setContent(news.getContent());
				obj.setAuthor(news.getAuthor());
				response.add(obj);
			}
		}
		return response;*/
	}

	@Override
	public Iterable getRepostsByNewsIdAndUserId(int userId, int fromNewsId, int toNewsId) {
		return null;
	}

	@Override
	public Iterable getLoopsByNewsIdAndUserId(int userId, int fromNewsId, int toNewsId) {
		return null;
	}

	@Override
	public Iterable getPoopsByNewsIdAndUserId(int userId, int fromNewsId, int toNewsId) {
		return null;
	}

	@Override
	public int likeNewsByNewsIdAndUserId() {
		return 0;
	}

	@Override
	public int repostNewsByNewsIdAndUserId() {
		return 0;
	}
}