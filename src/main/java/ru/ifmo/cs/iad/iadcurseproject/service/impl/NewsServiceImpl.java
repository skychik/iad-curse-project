package ru.ifmo.cs.iad.iadcurseproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ifmo.cs.iad.iadcurseproject.entity.*;
import ru.ifmo.cs.iad.iadcurseproject.repository.NewsRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.SubscriptionRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.UserRepository;
import ru.ifmo.cs.iad.iadcurseproject.service.NewsService;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class NewsServiceImpl implements NewsService {
	private final NewsRepository newsRepository;
	private final UserRepository userRepository;
	private final SubscriptionRepository subscriptionRepository;

	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository, UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
		this.newsRepository = newsRepository;
		this.userRepository = userRepository;
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public Iterable getAllNews(int userId, int fromNewsId, int toNewsId) {
		// TODO: write this in one SQL request
		List<News> newsList;
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
		return response;
	}
}
