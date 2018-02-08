package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.cs.iad.iadcurseproject.entity.News;
import ru.ifmo.cs.iad.iadcurseproject.entity.User;
import ru.ifmo.cs.iad.iadcurseproject.repository.NewsRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.PerformerRepository;
import ru.ifmo.cs.iad.iadcurseproject.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CRUDWebController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PerformerRepository performerRepository;
	@Autowired
	private NewsRepository newsRepository;

	@RequestMapping(value = "/all_news", method = RequestMethod.GET)
	public List<News> getAllNews(@RequestParam(value = "userId", required = false, defaultValue = "-1") int userId,
	                             @RequestParam(value = "from", required = false, defaultValue = "0") int fromNewsId,
	                             @RequestParam(value = "to") int toNewsId) {
		List<News> allNews;
		if (userId == -1) {
			allNews = newsRepository.findAll(); //TODO: remove
		} else {
			User user = userRepository.findOne(Long.valueOf(userId));
			Set<Long> subscriptionIds = new HashSet<>();
			for (User u : user.getSubscriptions()) {
				subscriptionIds.add(u.getId());
			}
			allNews = newsRepository.findAllByAuthorIdIn(subscriptionIds);
		}

		allNews = allNews.subList(fromNewsId, toNewsId + 1);
//		for (News news : allNews) {
//			if (news.getContent().length() > 120) {
//				news.setContent(news.getContent().substring(0, 119) + "...");
//			}
//		}
		return allNews;
	}

//	@RequestMapping(value = "/")
}
